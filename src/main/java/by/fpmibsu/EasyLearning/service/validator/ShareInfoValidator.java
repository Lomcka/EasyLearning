package by.fpmibsu.EasyLearning.service.validator;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.ShareInfoBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import org.json.simple.JSONObject;

public class ShareInfoValidator implements Validator<ShareInfoBean>{
    @Override
    public ShareInfoBean validate(JSONObject json) throws IncorrectFormDataException {
        ShareInfoBean info = new ShareInfoBean();

        String login = (String) json.get("username");
        if (login != null && !login.isEmpty()) {
            info.setLogin(login);
        } else {
            throw new IncorrectFormDataException("login", login);
        }

        String keyWord = (String) json.get("password");
        if (keyWord != null && !keyWord.isEmpty()) {
            info.setKeyWord(keyWord);
        } else {
            throw new IncorrectFormDataException("keyWord", keyWord);
        }

        return info;
    }
}

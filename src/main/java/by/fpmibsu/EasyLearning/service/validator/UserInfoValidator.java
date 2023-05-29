package by.fpmibsu.EasyLearning.service.validator;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.UserInfoBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import org.json.simple.JSONObject;

public class UserInfoValidator implements Validator<UserInfoBean> {

    @Override
    public UserInfoBean validate(JSONObject json) throws IncorrectFormDataException {
        UserInfoBean user = new UserInfoBean();

        String login = (String) json.get("username");
        if (login != null && !login.isEmpty()) {
            user.setLogin(login);
        } else {
            throw new IncorrectFormDataException("login", login);
        }

        String password = (String) json.get("password");
        if (password != null && !password.isEmpty()) {
            user.setPassword(password);
        } else {
            throw new IncorrectFormDataException("password", password);
        }

        String keyWord = (String) json.get("security-code");
        if (keyWord != null && !keyWord.isEmpty()) {
            user.setKeyWord(keyWord);
        } else {
            throw new IncorrectFormDataException("keyWord", keyWord);
        }

        return user;
    }
}

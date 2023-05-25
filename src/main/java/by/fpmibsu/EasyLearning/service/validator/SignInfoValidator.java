package by.fpmibsu.EasyLearning.service.validator;

import by.fpmibsu.EasyLearning.bean.SignInfoBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import org.json.simple.JSONObject;

public class SignInfoValidator implements Validator<SignInfoBean> {
    @Override
    public SignInfoBean validate(JSONObject json) throws IncorrectFormDataException {
        SignInfoBean signInfo = new SignInfoBean();

        String login = (String) json.get("username");
        if (login != null && !login.isEmpty()) {
            signInfo.setLogin(login);
        } else {
            throw new IncorrectFormDataException("login", login);
        }

        String password = (String) json.get("password");
        if (password != null && !password.isEmpty()) {
            signInfo.setPassword(password);
        } else {
            throw new IncorrectFormDataException("password", password);
        }

        return signInfo;
    }
}

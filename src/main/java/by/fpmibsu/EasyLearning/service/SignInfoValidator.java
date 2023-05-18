package by.fpmibsu.EasyLearning.service;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.SignInfoBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;

public class SignInfoValidator implements Validator<SignInfoBean> {
    @Override
    public SignInfoBean validate(HttpServletRequest request) throws IncorrectFormDataException {
        SignInfoBean signInfo = new SignInfoBean();

        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                signInfo.setId(Long.parseLong(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        } else {
            throw new IncorrectFormDataException("id", null);
        }

        parameter = request.getParameter("login");
        if (parameter != null && !parameter.isEmpty()) {
            signInfo.setLogin(parameter);
        } else {
            throw new IncorrectFormDataException("login", parameter);
        }

        parameter = request.getParameter("password");
        if (parameter != null && !parameter.isEmpty()) {
            signInfo.setPassword(parameter);
        } else {
            throw new IncorrectFormDataException("password", parameter);
        }

        return signInfo;
    }
}

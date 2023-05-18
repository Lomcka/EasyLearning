package by.fpmibsu.EasyLearning.service;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.UserInfoBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;

public class UserInfoValidator implements Validator<UserInfoBean> {

    @Override
    public UserInfoBean validate(HttpServletRequest request) throws IncorrectFormDataException {
        UserInfoBean user = new UserInfoBean();
        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                user.setId(Long.parseLong(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        } else {
            throw new IncorrectFormDataException("id", null);
        }

        parameter = request.getParameter("login");
        if (parameter != null && !parameter.isEmpty()) {
            user.setLogin(parameter);
        } else {
            throw new IncorrectFormDataException("login", parameter);
        }

        parameter = request.getParameter("password");
        if (parameter != null && !parameter.isEmpty()) {
            user.setPassword(parameter);
        } else {
            throw new IncorrectFormDataException("password", parameter);
        }

        parameter = request.getParameter("keyWord");
        if (parameter != null && !parameter.isEmpty()) {
            user.setKeyWord(parameter);
        } else {
            throw new IncorrectFormDataException("keyWord", parameter);
        }

        return user;
    }
}

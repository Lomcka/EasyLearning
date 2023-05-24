package by.fpmibsu.EasyLearning.service.validator;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.ShareInfoBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;

public class ShareInfoValidator implements Validator<ShareInfoBean>{
    @Override
    public ShareInfoBean validate(HttpServletRequest request) throws IncorrectFormDataException {
        ShareInfoBean info = new ShareInfoBean();

        String parameter = request.getParameter("id");
        if (parameter == null) {
            parameter = "0";
        }

        try {
            info.setId(Long.parseLong(parameter));
        } catch (NumberFormatException e) {
            throw new IncorrectFormDataException("id", parameter);
        }

        parameter = request.getParameter("login");
        if (parameter != null && !parameter.isEmpty()) {
            info.setLogin(parameter);
        } else {
            throw new IncorrectFormDataException("login", parameter);
        }

        parameter = request.getParameter("keyWord");
        if (parameter != null && !parameter.isEmpty()) {
            info.setKeyWord(parameter);
        } else {
            throw new IncorrectFormDataException("keyWord", parameter);
        }

        return info;
    }
}

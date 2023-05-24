package by.fpmibsu.EasyLearning.service.validator;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.ModuleNameBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;

public class ModuleNameValidator implements Validator<ModuleNameBean> {
    @Override
    public ModuleNameBean validate(HttpServletRequest request) throws IncorrectFormDataException {
        ModuleNameBean moduleName = new ModuleNameBean();

        String parameter = request.getParameter("id");
        if (parameter == null) {
            parameter = "0";
        }

        try {
            moduleName.setId(Long.parseLong(parameter));
        } catch (NumberFormatException e) {
            throw new IncorrectFormDataException("id", parameter);
        }

        parameter = request.getParameter("moduleName");
        if (parameter != null && !parameter.isEmpty()) {
            moduleName.setModuleName(parameter);
        } else {
            throw new IncorrectFormDataException("moduleName", parameter);
        }

        return moduleName;
    }
}

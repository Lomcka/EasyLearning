package by.fpmibsu.EasyLearning.service.validator;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.ModuleNameBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import org.json.simple.JSONObject;

public class ModuleNameValidator implements Validator<ModuleNameBean> {
    @Override
    public ModuleNameBean validate(JSONObject json) throws IncorrectFormDataException {
        ModuleNameBean moduleName = new ModuleNameBean();

        String name = (String) json.get("module-name");
        if (name != null && !name.isEmpty()) {
            moduleName.setModuleName(name);
        } else {
            throw new IncorrectFormDataException("moduleName", name);
        }

        return moduleName;
    }
}

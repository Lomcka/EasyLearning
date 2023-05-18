package by.fpmibsu.EasyLearning.service.validator;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.FolderBean;
import by.fpmibsu.EasyLearning.bean.ModuleNameBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;

public class FolderValidator implements Validator<FolderBean> {
    @Override
    public FolderBean validate(HttpServletRequest request) throws IncorrectFormDataException {
        FolderBean folder = new FolderBean();

        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                folder.setId(Long.parseLong(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        } else {
            throw new IncorrectFormDataException("id", parameter);
        }

        parameter = request.getParameter("folderName");
        if (parameter != null && !parameter.isEmpty()) {
            folder.setFolderName(parameter);
        } else {
            throw new IncorrectFormDataException("folderName", parameter);
        }

        String[] moduleNames = request.getParameterValues("moduleNames");
        ArrayList<ModuleNameBean> modules = new ArrayList<>();

        for (var string : moduleNames) {
            if (string.equals("{") || string.equals("}") || string.equals("word") ||
                    string.equals("translation") || string.equals("id") ||
                    string.equals("=") || string.equals(",")) {
                continue;
            }

            if (modules.isEmpty()) {
                modules.add(new ModuleNameBean());
            }
            if (!modules.get(modules.size() - 1).getModuleName().isEmpty()) {
                modules.add(new ModuleNameBean());
            }

            if (modules.get(modules.size() - 1).getId() == 0) {
                try {
                    modules.get(modules.size() - 1).setId(Long.parseLong(string));
                } catch (NumberFormatException e) {
                    throw new IncorrectFormDataException("id", string);
                }
            } else if (modules.get(modules.size() - 1).getModuleName().isEmpty()) {
                if (!string.isEmpty()) {
                    modules.get(modules.size() - 1).setModuleName(string);
                } else {
                    throw new IncorrectFormDataException("moduleName", parameter);
                }
            }
        }

        return folder;
    }
}

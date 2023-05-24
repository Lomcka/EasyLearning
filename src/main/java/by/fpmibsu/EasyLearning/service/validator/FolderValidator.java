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
        if (parameter == null) {
            parameter = "0";
        }

        try {
            folder.setId(Long.parseLong(parameter));
        } catch (NumberFormatException e) {
            throw new IncorrectFormDataException("id", parameter);
        }

        parameter = request.getParameter("folderName");
        if (parameter != null && !parameter.isEmpty()) {
            folder.setFolderName(parameter);
        } else {
            throw new IncorrectFormDataException("folderName", parameter);
        }

        return folder;
    }
}

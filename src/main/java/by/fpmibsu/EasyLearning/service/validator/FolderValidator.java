package by.fpmibsu.EasyLearning.service.validator;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.FolderBean;
import by.fpmibsu.EasyLearning.bean.ModuleNameBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

        parameter = request.getParameter("modules");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";
        try {
            result = objectMapper.writeValueAsString(parameter);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ModuleNameBean[] moduleNames;
        try {
            moduleNames = objectMapper.readValue(result, ModuleNameBean[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        folder.setModules((ArrayList<ModuleNameBean>) Arrays.asList(moduleNames));

        return folder;
    }
}

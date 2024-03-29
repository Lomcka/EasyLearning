package by.fpmibsu.EasyLearning.service.validator;

import java.util.ArrayList;
import java.util.Arrays;

import by.fpmibsu.EasyLearning.bean.FolderBean;
import by.fpmibsu.EasyLearning.bean.ModuleNameBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import org.json.simple.JSONObject;

public class FolderValidator implements Validator<FolderBean> {
    @Override
    public FolderBean validate(JSONObject json) throws IncorrectFormDataException {
        FolderBean folder = new FolderBean();

//        parameter = request.getParameter("folderName");
//        if (parameter != null && !parameter.isEmpty()) {
//            folder.setFolderName(parameter);
//        } else {
//            throw new IncorrectFormDataException("folderName", parameter);
//        }
//
//        parameter = request.getParameter("modules");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String result = "";
//        try {
//            result = objectMapper.writeValueAsString(parameter);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        ModuleNameBean[] moduleNames;
//        try {
//            moduleNames = objectMapper.readValue(result, ModuleNameBean[].class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        folder.setModules((ArrayList<ModuleNameBean>) Arrays.asList(moduleNames));

        return folder;
    }
}

package by.fpmibsu.EasyLearning.action;

import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.FolderService;
import by.fpmibsu.EasyLearning.service.ModuleService;
import by.fpmibsu.EasyLearning.service.impl.FolderServiceImpl;
import by.fpmibsu.EasyLearning.service.impl.ModuleServiceImpl;
import org.json.simple.JSONObject;

public class AddAction implements Action {
    @Override
    public JSONObject act(JSONObject json, String queryType, Long userId) throws ServiceException, IncorrectFormDataException {
        return switch (queryType) {
            case "add-new-module" -> addNewModule(json, userId);
            case "add-new-folder" -> addNewFolder(json, userId);
            default -> null;
        };
    }

    private JSONObject addNewModule(JSONObject json, Long userId) throws ServiceException {
        String moduleName = (String) json.get("moduleName");
        if (moduleName == null || moduleName.isEmpty()) {
            throw new RuntimeException();
        }
        ModuleService moduleService = new ModuleServiceImpl();
        moduleService.create(moduleName, userId);

        System.out.println("Module added successfully");
        return new JSONObject();
    }

    private JSONObject addNewFolder(JSONObject json, Long userId) throws ServiceException {
        String folderName = (String) json.get("folderName");
        if (folderName == null || folderName.isEmpty()) {
            throw new RuntimeException();
        }
        FolderService folderService = new FolderServiceImpl();
        folderService.create(folderName, userId);

        System.out.println("Folder added successfully");
        return new JSONObject();
    }
}

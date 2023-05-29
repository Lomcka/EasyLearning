package by.fpmibsu.EasyLearning.action;

import by.fpmibsu.EasyLearning.bean.FolderBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.FolderService;
import by.fpmibsu.EasyLearning.service.ModuleService;
import by.fpmibsu.EasyLearning.service.impl.FolderServiceImpl;
import by.fpmibsu.EasyLearning.service.impl.ModuleServiceImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FolderAction implements Action {
    @Override
    public JSONObject act(JSONObject json, String queryType, Long userId) throws ServiceException, IncorrectFormDataException {
        return switch (queryType) {
            case "merge-folders" -> mergeFolders(json, userId);
            case "get-modules" -> getModules(json, userId);
            case "add-module-to-folder" -> addModuleToFolder(json, userId);
            default -> null;
        };
    }

    private JSONObject mergeFolders(JSONObject json, Long userId) {
        throw new UnsupportedOperationException();
    }

    private JSONObject getModules(JSONObject json, Long userId) throws ServiceException {
        String folderName = (String) json.get("folderName");
        FolderService folderService = new FolderServiceImpl();
        var folder = folderService.findByName(folderName);
        if (folder.isEmpty()) {
            throw new RuntimeException();
        }

        JSONObject result = new JSONObject();
        result.put("folderName", folderName);
        result.put("modules", getModuleNames(folder.get()));
        return result;
    }

    private JSONArray getModuleNames(FolderBean folderBean) {
        JSONArray modules = new JSONArray();
        folderBean.getModules().forEach(moduleNameBean -> {
            JSONObject moduleJson = new JSONObject();
            moduleJson.put("name", moduleNameBean.getModuleName());
            modules.add(moduleJson);
        });
        return modules;
    }

    private JSONObject addModuleToFolder(JSONObject json, Long userId) throws ServiceException {
        String folderName = (String) json.get("folderName");
        String moduleName = (String) json.get("moduleName");
        ModuleService moduleService = new ModuleServiceImpl();
        FolderService folderService = new FolderServiceImpl();

        var module = moduleService.findByName(moduleName);
        if (module.isEmpty()) {
            throw new RuntimeException();
        }
        var folder = folderService.findByName(folderName);
        if (folder.isEmpty()) {
            throw new RuntimeException();
        }

        folderService.addModule(folder.get().getId(), module.get().getId());
        return new JSONObject();
    }
}

package by.fpmibsu.EasyLearning.action;

import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.FolderService;
import by.fpmibsu.EasyLearning.service.ModuleService;
import by.fpmibsu.EasyLearning.service.impl.FolderServiceImpl;
import by.fpmibsu.EasyLearning.service.impl.ModuleServiceImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class GetAllAction implements Action {
    @Override
    public JSONObject act(JSONObject json, String queryType, Long userId) throws ServiceException, IncorrectFormDataException {
        ModuleService moduleService = new ModuleServiceImpl();
        var modules = moduleService.findByOwnerId(userId);
        modules.addAll(moduleService.findByReaderId(userId));
        var modulesJson = toOurFormat(modules);
        FolderService folderService = new FolderServiceImpl();
        var folders = folderService.findByOwnerId(userId);
        var foldersJson = toOurFormat(folders);

        JSONObject result = new JSONObject();
        result.put("modules", modulesJson);
        result.put("folders", foldersJson);
        return result;
    }

    private JSONArray toOurFormat(ArrayList<String> array) {
        JSONArray result = new JSONArray();
        array.forEach(element -> {
            JSONObject obj = new JSONObject();
            obj.put("name", element);
            result.add(obj);
        });
        return result;
    }
}

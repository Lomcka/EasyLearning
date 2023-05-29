package by.fpmibsu.EasyLearning.action;

import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.ModuleService;
import by.fpmibsu.EasyLearning.service.ShareInfoService;
import by.fpmibsu.EasyLearning.service.impl.ModuleServiceImpl;
import by.fpmibsu.EasyLearning.service.impl.ShareInfoServiceImpl;
import by.fpmibsu.EasyLearning.service.validator.ShareInfoValidator;
import org.json.simple.JSONObject;

public class ShareAction implements Action {
    @Override
    public JSONObject act(JSONObject json, String queryType, Long userId) throws ServiceException, IncorrectFormDataException {
        ModuleService moduleService = new ModuleServiceImpl();
        ShareInfoValidator validator = new ShareInfoValidator();
        var readerInfo = validator.validate(json);
        String moduleName = (String) json.get("moduleName");

        var result = new JSONObject();
        var module = moduleService.findByName(moduleName);
        if (module.isEmpty()) {
            result.put("status", "not success");
            return result;
        }

        ShareInfoService shareInfoService = new ShareInfoServiceImpl();
        var shareInfo = shareInfoService.findByLogin(readerInfo.getLogin());
        if (shareInfo.isEmpty() || !shareInfo.get().getKeyWord().equals(readerInfo.getKeyWord())) {
            result.put("status", "not success");
            return result;
        }

        var modules = moduleService.findByReaderId(shareInfo.get().getId());
        if (modules.contains(moduleName)) {
            result.put("status", "exists");
            return result;
        }

        moduleService.addReader(module.get().getId(), shareInfo.get().getId());
        result.put("status", "success");
        return result;
    }
}

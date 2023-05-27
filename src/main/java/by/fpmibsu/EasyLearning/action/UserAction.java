package by.fpmibsu.EasyLearning.action;

import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.ShareInfoService;
import by.fpmibsu.EasyLearning.service.SignInfoService;
import by.fpmibsu.EasyLearning.service.UserInfoService;
import by.fpmibsu.EasyLearning.service.impl.ShareInfoServiceImpl;
import by.fpmibsu.EasyLearning.service.impl.SignInfoServiceImpl;
import by.fpmibsu.EasyLearning.service.impl.UserInfoServiceImpl;
import by.fpmibsu.EasyLearning.service.validator.SignInfoValidator;
import by.fpmibsu.EasyLearning.service.validator.UserInfoValidator;
import org.json.simple.JSONObject;

public class UserAction implements Action {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    @Override
    public JSONObject act(JSONObject json, String queryType, Long userId) throws ServiceException, IncorrectFormDataException {
        return switch (queryType) {
            case "check-account" -> checkAccount(json);
            case "create-account" -> createAccount(json);
            case "change-login" -> changeLogin(json, userId);
            case "change-password" -> changePassword(json, userId);
            case "change-security-word" -> changeKeyWord(json, userId);
            case "get-data-for-settings" -> getLoginAndKeyword(json, userId);
            default -> null;
        };
    }

    private JSONObject checkAccount(JSONObject json) throws IncorrectFormDataException, ServiceException {
        SignInfoValidator validator = new SignInfoValidator();
        var signInfoJson = validator.validate(json);

        SignInfoService service = new SignInfoServiceImpl();
        var signInfo = service.findByLogin(signInfoJson.getLogin());

        JSONObject response = new JSONObject();
        if (signInfo.isEmpty() || !signInfo.get().getPassword().equals(signInfoJson.getPassword())) {
            response.put("success", false);
        } else {
            userId = signInfo.get().getId();
            response.put("success", true);
        }
        return response;
    }

    private JSONObject createAccount(JSONObject json) throws IncorrectFormDataException, ServiceException {
        UserInfoValidator validator = new UserInfoValidator();
        var userInfoJson = validator.validate(json);

        SignInfoService service = new SignInfoServiceImpl();
        var signInfoService = service.findByLogin(userInfoJson.getLogin());

        JSONObject response = new JSONObject();
        if (signInfoService.isPresent()) {
            response.put("exists", true);
            return response;
        }
        response.put("exists", false);

        UserInfoService userInfoService = new UserInfoServiceImpl();
        userInfoService.create(userInfoJson);
        userId = service.findByLogin(userInfoJson.getLogin()).get().getId();
        return response;
    }

    private JSONObject changeLogin(JSONObject json, Long userId) {
        String newLogin = (String) json.get("login");

        ShareInfoService service = new ShareInfoServiceImpl();
        JSONObject response = new JSONObject();
        try {
            service.updateLogin(userId, newLogin);
            response.put("success", true);
        } catch (ServiceException e) {
            response.put("success", false);
        }
        return response;
    }

    private JSONObject changePassword(JSONObject json, Long userId) throws ServiceException {
        String currentPassword = (String) json.get("currentPassword");
        String newPassword = (String) json.get("newPassword");

        UserInfoService service = new UserInfoServiceImpl();
        var user = service.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException();
        }

        JSONObject response = new JSONObject();
        if (!user.get().getPassword().equals(currentPassword)) {
            response.put("success", false);
            return response;
        }

        try {
            service.updatePassword(userId, newPassword);
        } catch (ServiceException e) {
            response.put("success", false);
            return response;
        }

        response.put("success", true);
        return response;
    }

    private JSONObject changeKeyWord(JSONObject json, Long userId) {
        String newKeyWord = (String) json.get("securityWord");

        ShareInfoService service = new ShareInfoServiceImpl();
        JSONObject response = new JSONObject();
        try {
            service.updateKeyword(userId, newKeyWord);
            response.put("success", true);
        } catch (ServiceException e) {
            response.put("success", false);
        }
        return response;
    }

    private JSONObject getLoginAndKeyword(JSONObject json, Long userId) throws ServiceException {
        ShareInfoService service = new ShareInfoServiceImpl();
        var shareInfo = service.findById(userId);
        if (shareInfo.isEmpty()) {
            throw new RuntimeException();
        }
        JSONObject response = new JSONObject();
        response.put("login", shareInfo.get().getLogin());
        response.put("securityWord", shareInfo.get().getKeyWord());
        return response;
    }
}

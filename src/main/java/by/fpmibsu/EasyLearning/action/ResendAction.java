package by.fpmibsu.EasyLearning.action;

import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import org.json.simple.JSONObject;

public class ResendAction implements Action {
    @Override
    public JSONObject act(JSONObject json, String queryType, Long userId) throws ServiceException, IncorrectFormDataException {
        return switch (queryType) {
            case "resend-ok-repeat" -> json;
            case "resend-ok-repeat2" -> new JSONObject();
            default -> null;
        };
    }
}

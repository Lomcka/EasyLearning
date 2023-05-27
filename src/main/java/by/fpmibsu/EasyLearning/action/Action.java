package by.fpmibsu.EasyLearning.action;

import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface Action {
    JSONObject act(JSONObject json, String queryType, Long userId) throws ServiceException, IncorrectFormDataException;
}

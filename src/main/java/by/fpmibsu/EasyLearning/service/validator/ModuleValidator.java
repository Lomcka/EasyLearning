package by.fpmibsu.EasyLearning.service.validator;

import java.util.ArrayList;
import java.util.Arrays;

import by.fpmibsu.EasyLearning.bean.CardBean;
import by.fpmibsu.EasyLearning.bean.ModuleBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import org.json.simple.JSONObject;

public class ModuleValidator implements Validator<ModuleBean> {
    @Override
    public ModuleBean validate(JSONObject json) throws IncorrectFormDataException {
        ModuleBean module = new ModuleBean();

//        parameter = request.getParameter("moduleName");
//        if (parameter != null && !parameter.isEmpty()) {
//            module.setModuleName(parameter);
//        } else {
//            throw new IncorrectFormDataException("moduleName", parameter);
//        }
//
//        parameter = request.getParameter("cards");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String result = "";
//        try {
//            result = objectMapper.writeValueAsString(parameter);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        CardBean[] cards;
//        try {
//            cards = objectMapper.readValue(result, CardBean[].class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        module.setCards((ArrayList<CardBean>) Arrays.asList(cards));

        return module;
    }
}

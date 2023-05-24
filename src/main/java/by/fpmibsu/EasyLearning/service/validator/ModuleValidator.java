package by.fpmibsu.EasyLearning.service.validator;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.CardBean;
import by.fpmibsu.EasyLearning.bean.ModuleBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ModuleValidator implements Validator<ModuleBean> {
    @Override
    public ModuleBean validate(HttpServletRequest request) throws IncorrectFormDataException {
        ModuleBean module = new ModuleBean();

        String parameter = request.getParameter("id");
        if (parameter == null) {
            parameter = "0";
        }

        try {
            module.setId(Long.parseLong(parameter));
        } catch (NumberFormatException e) {
            throw new IncorrectFormDataException("id", parameter);
        }

        parameter = request.getParameter("moduleName");
        if (parameter != null && !parameter.isEmpty()) {
            module.setModuleName(parameter);
        } else {
            throw new IncorrectFormDataException("moduleName", parameter);
        }

        parameter = request.getParameter("cards");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";
        try {
            result = objectMapper.writeValueAsString(parameter);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        CardBean[] cards;
        try {
            cards = objectMapper.readValue(result, CardBean[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        module.setCards((ArrayList<CardBean>) Arrays.asList(cards));

        return module;
    }
}

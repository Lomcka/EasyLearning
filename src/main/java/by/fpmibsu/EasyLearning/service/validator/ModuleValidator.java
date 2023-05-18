package by.fpmibsu.EasyLearning.service.validator;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.CardBean;
import by.fpmibsu.EasyLearning.bean.ModuleBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;

public class ModuleValidator implements Validator<ModuleBean> {
    @Override
    public ModuleBean validate(HttpServletRequest request) throws IncorrectFormDataException {
        ModuleBean module = new ModuleBean();

        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                module.setId(Long.parseLong(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        } else {
            throw new IncorrectFormDataException("id", null);
        }

        parameter = request.getParameter("moduleName");
        if (parameter != null && !parameter.isEmpty()) {
            module.setModuleName(parameter);
        } else {
            throw new IncorrectFormDataException("moduleName", parameter);
        }

        String[] cards = request.getParameterValues("cards");
        ArrayList<CardBean> list = new ArrayList<>();
        for (var string : cards) {
            if (string.equals("{") || string.equals("}") || string.equals("word") ||
                string.equals("translation") || string.equals("id") ||
                string.equals("=") || string.equals(",")) {
                continue;
            }

            if (list.isEmpty()) {
                list.add(new CardBean());
            }
            if (list.get(list.size() - 1).getTranslation().isEmpty()) {
                list.add(new CardBean());
            }

            if (list.get(list.size() - 1).getId() == 0) {
                try {
                    list.get(list.size() - 1).setId(Long.parseLong(string));
                } catch (NumberFormatException e) {
                    throw new IncorrectFormDataException("id", string);
                }
            } else if (list.get(list.size() - 1).getWord().isEmpty()) {
                if (!string.isEmpty()) {
                    list.get(list.size() - 1).setWord(string);
                } else {
                    throw new IncorrectFormDataException("word", string);
                }
            } else {
                if (!string.isEmpty()) {
                    list.get(list.size() - 1).setTranslation(string);
                } else {
                    throw new IncorrectFormDataException("translation", string);
                }
            }
        }

        return module;
    }
}

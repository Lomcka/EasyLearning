package by.fpmibsu.EasyLearning.service.validator;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.CardBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;

public class CardValidator implements Validator<CardBean> {
    @Override
    public CardBean validate(HttpServletRequest request) throws IncorrectFormDataException {
        CardBean card = new CardBean();

        String parameter = request.getParameter("id");
        if (parameter == null) {
            parameter = "0";
        }

        try {
            card.setId(Long.parseLong(parameter));
        } catch (NumberFormatException e) {
            throw new IncorrectFormDataException("id", parameter);
        }

        parameter = request.getParameter("word");
        if (parameter != null && !parameter.isEmpty()) {
            card.setWord(parameter);
        } else {
            throw new IncorrectFormDataException("word", parameter);
        }

        return card;
    }
}

package by.fpmibsu.EasyLearning.service;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.CardBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;

public class CardValidator implements Validator<CardBean> {
    @Override
    public CardBean validate(HttpServletRequest request) throws IncorrectFormDataException {
        CardBean card = new CardBean();

        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                card.setId(Long.parseLong(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        } else {
            throw new IncorrectFormDataException("id", null);
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

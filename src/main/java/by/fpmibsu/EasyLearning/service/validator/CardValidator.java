package by.fpmibsu.EasyLearning.service.validator;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.CardBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import org.json.simple.JSONObject;

public class CardValidator implements Validator<CardBean> {
    @Override
    public CardBean validate(JSONObject json) throws IncorrectFormDataException {
        CardBean card = new CardBean();

        String word = (String) json.get("word");
        if (word != null && !word.isEmpty()) {
            card.setWord(word);
        } else {
            throw new IncorrectFormDataException("word", word);
        }

        String translation = (String) json.get("translation");
        if (translation != null && !translation.isEmpty()) {
            card.setTranslation(translation);
        } else {
            throw new IncorrectFormDataException("translation", translation);
        }

        return card;
    }
}

package by.fpmibsu.EasyLearning.action;

import by.fpmibsu.EasyLearning.bean.ModuleBean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.ModuleService;
import by.fpmibsu.EasyLearning.service.impl.ModuleServiceImpl;
import by.fpmibsu.EasyLearning.service.validator.CardValidator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ModuleAction implements Action {
    @Override
    public JSONObject act(JSONObject json, String queryType, Long userId) throws ServiceException, IncorrectFormDataException {
        return switch (queryType) {
            case "merge-modules" -> mergeModules(json, userId);
            case "get-module-data", "get-cards-to-repeat" -> getModuleData(json, userId, queryType);
            case "add-Card" -> addCard(json, userId);
            case "resend-ok-repeat" -> json;
            case "resend-ok-repeat2" -> new JSONObject();
            default -> null;
        };
    }

    private JSONObject getModuleData(JSONObject json, Long userId, String queryType) throws ServiceException {
        String moduleName = (String) json.get("moduleName");
        ModuleService moduleService = new ModuleServiceImpl();
        var module = moduleService.findByName(moduleName);
        if (module.isEmpty()) {
            throw new RuntimeException();
        }

        var moduleData = moduleService.findById(module.get().getId());
        if (moduleData.isEmpty()) {
            throw new RuntimeException();
        }

        JSONObject result = new JSONObject();
        if (queryType.equals("get-module-data")) {
            result.put("name", moduleName);
            result.put("content", getCards(module.get()));
        } else {
            result.put("repeat", getCards(module.get()));
            result.put("ok", new JSONObject());
        }
        return result;
    }

    private JSONArray getCards(ModuleBean module) {
        JSONArray cards = new JSONArray();
        module.getCards().forEach(card -> {
            JSONObject cardJson = new JSONObject();
            cardJson.put("word", card.getWord());
            cardJson.put("translation", card.getTranslation());
            cards.add(cardJson);
        });
        return cards;
    }

    private JSONObject addCard(JSONObject json, Long userId) throws IncorrectFormDataException, ServiceException {
        CardValidator validator = new CardValidator();
        var newCard = validator.validate(json);
        String moduleName = (String) json.get("moduleName");

        ModuleService moduleService = new ModuleServiceImpl();
        var module = moduleService.findByName(moduleName);
        if (module.isEmpty()) {
            throw new RuntimeException();
        }

        moduleService.addCard(module.get().getId(), newCard);
        return new JSONObject();
    }

    private JSONObject mergeModules(JSONObject json, Long userId) {
        return null;
    }
}

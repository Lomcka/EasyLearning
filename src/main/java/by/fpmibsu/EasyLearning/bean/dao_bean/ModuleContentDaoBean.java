package by.fpmibsu.EasyLearning.bean.dao_bean;

import by.fpmibsu.EasyLearning.bean.Bean;

public class ModuleContentDaoBean extends Bean {
    public ModuleContentDaoBean() {
        moduleId = 0L;
        cardId = 0L;
        word = "";
        translation = "";
    }

    public ModuleContentDaoBean(Long moduleId, Long cardId, String word, String translation) {
        if (moduleId == null) {
            throw new IllegalArgumentException("moduleId is null");
        }
        if (cardId == null) {
            throw new IllegalArgumentException("cardId is null");
        }
        if (word == null) {
            throw new IllegalArgumentException("word is null");
        }
        if (translation == null) {
            throw new IllegalArgumentException("translation is null");
        }

        this.moduleId = moduleId;
        this.cardId = cardId;
        this.word = word;
        this.translation = translation;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public Long getCardId() {
        return cardId;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setModuleId(Long moduleId) {
        if (moduleId == null) {
            throw new IllegalArgumentException("moduleId is null");
        }

        this.moduleId = moduleId;
    }

    public void setCardId(Long cardId) {
        if (cardId == null) {
            throw new IllegalArgumentException("cardId is null");
        }

        this.cardId = cardId;
    }

    public void setWord(String word) {
        if (word == null) {
            throw new IllegalArgumentException("word is null");
        }

        this.word = word;
    }

    public void setTranslation(String translation) {
        if (translation == null) {
            throw new IllegalArgumentException("translation is null");
        }

        this.translation = translation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ModuleContentDaoBean content = (ModuleContentDaoBean) obj;
        return moduleId.equals(content.moduleId) && cardId.equals(content.cardId)
                && word.equals(content.word) && translation.equals(content.translation);
    }

    @Override
    public String toString() {
        return "ModuleContentDaoBean{" +
                "moduleId=" + moduleId +
                ", cardId=" + cardId +
                ", word='" + word + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }

    private Long moduleId;
    private Long cardId;
    private String word;
    private String translation;
}

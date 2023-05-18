package by.fpmibsu.EasyLearning.bean;

public class CardBean extends Bean {
    public CardBean() {
        moduleId = 0L;
        word = "";
        translation = "";
    }

    public CardBean(Long id, Long moduleId, String word, String translation) {
        super(id);
        if (moduleId == null) {
            throw new IllegalArgumentException("moduleId is null");
        }

        if (word == null) {
            throw new IllegalArgumentException("word is null");
        }

        if (translation == null) {
            throw new IllegalArgumentException("translation is null");
        }

        this.moduleId = moduleId;
        this.word = word;
        this.translation = translation;
    }

    public CardBean(CardBean cardBean) {
        if (cardBean == null) {
            throw new IllegalArgumentException("cardBean is null");
        }

        id = cardBean.getId();
        moduleId = cardBean.getModuleId();
        word = cardBean.getWord();
        translation = cardBean.getTranslation();
    }

    public Long getModuleId() {
        return moduleId;
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
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        CardBean otherCard = (CardBean) other;

        return id.equals(otherCard.id) && moduleId.equals(otherCard.moduleId) &&
                word.equals(otherCard.word) && translation.equals(otherCard.translation);
    }

    @Override
    public String toString() {
        return "CardBean{" +
                "id=" + id +
                ", moduleId='" + moduleId + '\'' +
                ", word='" + word + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }

    Long moduleId;
    private String word;
    private String translation;
}

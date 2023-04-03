package by.fpmibsu.EasyLearning.entity;

public class CardInfoBean extends Bean {
    public CardInfoBean() {
        word = "";
        translation = "";
    }

    public CardInfoBean(Long id, String word, String translation) {
        super(id);
        if (word == null) {
            throw new IllegalArgumentException("word is null");
        }

        if (translation == null) {
            throw new IllegalArgumentException("translation is null");
        }

        this.word = word;
        this.translation = translation;
    }

    public CardInfoBean(CardInfoBean cardBean) {
        if (cardBean == null) {
            throw new IllegalArgumentException("cardBean is null");
        }

        id = cardBean.getId();
        word = cardBean.getWord();
        translation = cardBean.getTranslation();
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
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
    public String toString() {
        return id.toString() + " " + word + " " + translation;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        CardInfoBean otherCard = (CardInfoBean) other;

        return id.equals(otherCard.getId()) && word.equals(otherCard.word) && translation.equals(otherCard.translation);
    }

    private String word;
    private String translation;
}

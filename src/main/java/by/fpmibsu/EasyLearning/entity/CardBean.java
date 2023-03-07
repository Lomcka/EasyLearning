package by.fpmibsu.EasyLearning.entity;

public class CardBean {
    public CardBean() {
        word = "";
        translation = "";
    }

    public CardBean(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public CardBean(CardBean cardBean) {
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
            throw new IllegalArgumentException("word points to null");
        }

        this.word = word;
    }

    public void setTranslation(String translation) {
        if (translation == null) {
            throw new IllegalArgumentException("translation points to null");
        }

        this.translation = translation;
    }

    @Override
    public String toString() {
        return word + " " + translation;
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

        return word.equals(otherCard.word) && translation.equals(otherCard.translation);
    }

    private String word;
    private String translation;
}

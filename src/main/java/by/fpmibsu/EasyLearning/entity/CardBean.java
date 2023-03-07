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

    public CardBean(CardBean cardInfo) {
        this.word = cardInfo.getWord();
        this.translation = cardInfo.getTranslation();
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCard(CardBean word) {
        this.word = word.getWord();
        this.translation = word.getTranslation();
    }

    public void setTranslation(String translation) {
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

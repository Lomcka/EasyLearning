package by.fpmibsu.EasyLearning.entity;

public class CardInfo {
    public CardInfo() {
        word = "";
        translation = "";
    }

    public CardInfo(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setCard(String word) {
        this.word = word;
    }

    public void setCard(CardInfo word) {
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

        CardInfo otherWord = (CardInfo) other;

        return word.equals(otherWord.word) && translation.equals(otherWord.translation);
    }

    private String word;
    private String translation;
}

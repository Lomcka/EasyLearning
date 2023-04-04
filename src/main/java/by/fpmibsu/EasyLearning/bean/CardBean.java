package by.fpmibsu.EasyLearning.bean;

public class CardBean extends Bean {
    public CardBean() {
        word = "";
        translation = "";
    }

    public CardBean(Long id, String word, String translation) {
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

    public CardBean(CardBean cardBean) {
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
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        CardBean otherCard = (CardBean) other;

        return id.equals(otherCard.getId()) && word.equals(otherCard.word) && translation.equals(otherCard.translation);
    }

    @Override
    public String toString() {
        return "CardBean{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }

    private String word;
    private String translation;
}

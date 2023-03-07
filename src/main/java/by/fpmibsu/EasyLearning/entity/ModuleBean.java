package by.fpmibsu.EasyLearning.entity;

import java.util.ArrayList;

public class ModuleBean {
    public ModuleBean() {
        cards = new ArrayList<>();
        moduleName = "";
    }

    public ModuleBean(String moduleName, ArrayList<CardBean> cards) {
        this.cards = new ArrayList<>(cards);
        this.moduleName = moduleName;
    }

    public ModuleBean(ModuleBean moduleBean) {
        cards = moduleBean.getCards();
        moduleName = moduleBean.getModuleName();
    }

    public ArrayList<CardBean> getCards() {
        return new ArrayList<>(cards);
    }

    public CardBean getCard(int index) {
        return new CardBean(cards.get(index));
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setCards(ArrayList<CardBean> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("ArrayList cards points to null");
        }

        this.cards = new ArrayList<>(cards);
    }

    public void setCard(CardBean card, int index) {
        if (card == null) {
            throw new IllegalArgumentException("CardBean points to null");
        }

        cards.set(index, card);
    }

    public void setModuleName(String moduleName) {
        if (moduleName == null) {
            throw new IllegalArgumentException("moduleName points to null");
        }

        this.moduleName = moduleName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ModuleBean module = (ModuleBean) obj;

        return cards.equals(module.getCards()) && moduleName.equals(module.getModuleName());
    }

    private ArrayList<CardBean> cards;
    private String moduleName;
}

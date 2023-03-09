package by.fpmibsu.EasyLearning.entity;

import java.util.ArrayList;

public class ModuleBean {
    public ModuleBean() {
        cards = new ArrayList<>();
        moduleName = "";
    }

    public ModuleBean(String moduleName, ArrayList<CardBean> cards) {
        if (moduleName == null) {
            throw new IllegalArgumentException("moduleName is null");
        }

        if (cards == null) {
            throw new IllegalArgumentException("ArrayList cards is null");
        }

        this.cards = new ArrayList<>(cards);
        this.moduleName = moduleName;
    }

    public ModuleBean(ModuleBean moduleBean) {
        if (moduleBean == null) {
            throw new IllegalArgumentException("moduleBean is null");
        }

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
            throw new IllegalArgumentException("ArrayList cards is null");
        }

        this.cards = new ArrayList<>(cards);
    }

    public void setCard(CardBean card, int index) {
        if (card == null) {
            throw new IllegalArgumentException("CardBean is null");
        }

        cards.set(index, new CardBean(card));
    }

    public void setModuleName(String moduleName) {
        if (moduleName == null) {
            throw new IllegalArgumentException("moduleName is null");
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

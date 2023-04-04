package by.fpmibsu.EasyLearning.bean;

import java.util.ArrayList;

public class ModuleBean extends Bean {
    public ModuleBean() {
        cards = new ArrayList<>();
        moduleName = "";
    }

    public ModuleBean(Long id, String moduleName, ArrayList<CardBean> cards) {
        super(id);
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

        id = moduleBean.getId();
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

        return id.equals(module.getId()) &&
                cards.equals(module.getCards()) && moduleName.equals(module.getModuleName());
    }

    @Override
    public String toString() {
        return "ModuleBean{" +
                "id=" + id +
                ", moduleName='" + moduleName + '\'' +
                ", cards=" + cards +
                '}';
    }

    private ArrayList<CardBean> cards;
    private String moduleName;
}

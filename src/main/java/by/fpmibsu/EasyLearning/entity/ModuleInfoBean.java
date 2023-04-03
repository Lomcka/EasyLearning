package by.fpmibsu.EasyLearning.entity;

import java.util.ArrayList;

public class ModuleInfoBean extends Bean {
    public ModuleInfoBean() {
        cards = new ArrayList<>();
        moduleName = "";
    }

    public ModuleInfoBean(Long id, String moduleName, ArrayList<CardInfoBean> cards) {
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

    public ModuleInfoBean(ModuleInfoBean moduleBean) {
        if (moduleBean == null) {
            throw new IllegalArgumentException("moduleBean is null");
        }

        id = moduleBean.getId();
        cards = moduleBean.getCards();
        moduleName = moduleBean.getModuleName();
    }

    public ArrayList<CardInfoBean> getCards() {
        return new ArrayList<>(cards);
    }

    public CardInfoBean getCard(int index) {
        return new CardInfoBean(cards.get(index));
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setCards(ArrayList<CardInfoBean> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("ArrayList cards is null");
        }

        this.cards = new ArrayList<>(cards);
    }

    public void setCard(CardInfoBean card, int index) {
        if (card == null) {
            throw new IllegalArgumentException("CardBean is null");
        }

        cards.set(index, new CardInfoBean(card));
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

        ModuleInfoBean module = (ModuleInfoBean) obj;

        return id.equals(module.getId()) &&
                cards.equals(module.getCards()) && moduleName.equals(module.getModuleName());
    }

    private ArrayList<CardInfoBean> cards;
    private String moduleName;
}

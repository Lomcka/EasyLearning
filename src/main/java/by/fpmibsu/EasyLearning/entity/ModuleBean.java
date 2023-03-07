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

    public void setModule(ModuleBean module) {
        cards = module.getCards();
        moduleName = module.getModuleName();
    }
    public void setCards(ArrayList<CardBean> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void setCard(CardBean card, int index) {
        cards.get(index).setCard(card);
    }
    public void setModuleName(String moduleName) {
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

    @Override
    protected ModuleBean clone() {
        return new ModuleBean(moduleName, cards);
    }

    private ArrayList<CardBean> cards;
    private String moduleName;
}

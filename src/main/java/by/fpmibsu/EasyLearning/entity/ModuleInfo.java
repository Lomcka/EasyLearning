package by.fpmibsu.EasyLearning.entity;

import java.util.ArrayList;

public class ModuleInfo {
    public ModuleInfo() {
        cards = new ArrayList<>();
        moduleName = "";
    }

    public ModuleInfo(String moduleName, ArrayList<CardInfo> words) {
        this.cards = new ArrayList<>(words);
        this.moduleName = moduleName;
    }

    public ModuleInfo(ModuleInfo moduleInfo) {
        this.cards = moduleInfo.getCards();
        this.moduleName = moduleInfo.getModuleName();
    }

    public ArrayList<CardInfo> getCards() {
        return new ArrayList<>(cards);
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModule(ModuleInfo module) {
        this.cards = module.getCards();
        this.moduleName = module.getModuleName();
    }
    public void setCards(ArrayList<CardInfo> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void setCard(CardInfo card, int index) {
        this.cards.get(index).setCard(card);
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

        ModuleInfo info = (ModuleInfo) obj;

        return (cards.equals(info.getCards()) && moduleName.equals(info.getModuleName()));
    }

    @Override
    protected ModuleInfo clone() {
        return new ModuleInfo(moduleName, cards);
    }

    private ArrayList<CardInfo> cards;
    private String moduleName;
}

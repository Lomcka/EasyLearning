package by.fpmibsu.EasyLearning.entity;

import java.util.ArrayList;

public class FolderBean {
    public FolderBean() {
        modules = new ArrayList<ModuleBean>();
        folderName = "";
    }

    public FolderBean(String name, ArrayList<ModuleBean> modules) {
        this.modules = new ArrayList<ModuleBean>(modules);
        this.folderName = name;
    }

    public ArrayList<ModuleBean> getModules() {
        return new ArrayList<ModuleBean>(modules);
    }

    public String getFolderName() {
        return folderName;
    }

    public ModuleBean getModule(int index) {
        return new ModuleBean(modules.get(index));
    }

    public void setModules(ArrayList<ModuleBean> modules) {
        this.modules = new ArrayList<ModuleBean>(modules);
    }

    public void setModule(ModuleBean module, int index) {
        modules.get(index).setModule(module);
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        FolderBean folder = (FolderBean) obj;
        return modules.equals(folder.getModules()) && folderName.equals(folder.getFolderName());
    }
    private ArrayList<ModuleBean> modules;
    private String folderName;
}

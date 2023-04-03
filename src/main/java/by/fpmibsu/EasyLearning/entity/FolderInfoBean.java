package by.fpmibsu.EasyLearning.entity;

import java.util.ArrayList;

public class FolderInfoBean extends Bean {
    public FolderInfoBean() {
        modules = new ArrayList<ModuleInfoBean>();
        folderName = "";
    }

    public FolderInfoBean(Long id, String folderName, ArrayList<ModuleInfoBean> modules) {
        super(id);
        if (folderName == null) {
            throw new IllegalArgumentException("folderName is null");
        }

        if (modules == null) {
            throw new IllegalArgumentException("ArrayList modules is null");
        }

        this.modules = new ArrayList<ModuleInfoBean>(modules);
        this.folderName = folderName;
    }

    public ArrayList<ModuleInfoBean> getModules() {
        return new ArrayList<ModuleInfoBean>(modules);
    }

    public String getFolderName() {
        return folderName;
    }

    public ModuleInfoBean getModule(int index) {
        return new ModuleInfoBean(modules.get(index));
    }

    public void setModules(ArrayList<ModuleInfoBean> modules) {
        if (modules == null) {
            throw new IllegalArgumentException("ArrayList modules is null");
        }

        this.modules = new ArrayList<ModuleInfoBean>(modules);
    }

    public void setModule(ModuleInfoBean module, int index) {
        if (module == null) {
            throw new IllegalArgumentException("module is null");
        }

        modules.set(index, new ModuleInfoBean(module));
    }

    public void setFolderName(String folderName) {
        if (folderName == null) {
            throw new IllegalArgumentException("folderName is null");
        }

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

        FolderInfoBean folder = (FolderInfoBean) obj;
        return id.equals(folder.getId()) &&
                modules.equals(folder.getModules()) && folderName.equals(folder.getFolderName());
    }

    private ArrayList<ModuleInfoBean> modules;
    private String folderName;
}

package by.fpmibsu.EasyLearning.bean;

import java.util.ArrayList;

public class FolderBean extends Bean {
    public FolderBean() {
        modules = new ArrayList<ModuleNameBean>();
        folderName = "";
    }

    public FolderBean(Long id, String folderName, ArrayList<ModuleNameBean> modules) {
        super(id);
        if (folderName == null) {
            throw new IllegalArgumentException("folderName is null");
        }

        if (modules == null) {
            throw new IllegalArgumentException("ArrayList modules is null");
        }

        this.modules = new ArrayList<ModuleNameBean>(modules);
        this.folderName = folderName;
    }

    public ArrayList<ModuleNameBean> getModules() {
        return new ArrayList<ModuleNameBean>(modules);
    }

    public String getFolderName() {
        return folderName;
    }

    public ModuleNameBean getModule(int index) {
        return new ModuleNameBean(modules.get(index));
    }

    public void setModules(ArrayList<ModuleNameBean> modules) {
        if (modules == null) {
            throw new IllegalArgumentException("ArrayList modules is null");
        }

        this.modules = new ArrayList<ModuleNameBean>(modules);
    }

    public void setModule(ModuleNameBean module, int index) {
        if (module == null) {
            throw new IllegalArgumentException("module is null");
        }

        modules.set(index, new ModuleNameBean(module));
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

        FolderBean folder = (FolderBean) obj;
        return id.equals(folder.getId()) &&
                modules.equals(folder.getModules()) && folderName.equals(folder.getFolderName());
    }

    @Override
    public String toString() {
        return "FolderBean{" +
                "id=" + id +
                ", folderName='" + folderName + '\'' +
                ", modules=" + modules +
                '}';
    }

    private ArrayList<ModuleNameBean> modules;
    private String folderName;
}

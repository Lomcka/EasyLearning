package by.fpmibsu.EasyLearning.bean;

import java.util.ArrayList;

public class FolderBean extends Bean {
    public FolderBean() {
        modules = new ArrayList<ModuleBean>();
        folderName = "";
    }

    public FolderBean(Long id, String folderName, ArrayList<ModuleBean> modules) {
        super(id);
        if (folderName == null) {
            throw new IllegalArgumentException("folderName is null");
        }

        if (modules == null) {
            throw new IllegalArgumentException("ArrayList modules is null");
        }

        this.modules = new ArrayList<ModuleBean>(modules);
        this.folderName = folderName;
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
        if (modules == null) {
            throw new IllegalArgumentException("ArrayList modules is null");
        }

        this.modules = new ArrayList<ModuleBean>(modules);
    }

    public void setModule(ModuleBean module, int index) {
        if (module == null) {
            throw new IllegalArgumentException("module is null");
        }

        modules.set(index, new ModuleBean(module));
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

    private ArrayList<ModuleBean> modules;
    private String folderName;
}

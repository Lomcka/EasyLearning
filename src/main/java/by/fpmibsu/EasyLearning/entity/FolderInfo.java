package by.fpmibsu.EasyLearning.entity;

import java.util.ArrayList;

public class FolderInfo {
    public FolderInfo() {
        modules = new ArrayList<ModuleInfo>();
        folderName = "";
    }

    public FolderInfo(String name, ArrayList<ModuleInfo> modules) {
        this.modules = new ArrayList<ModuleInfo>(modules);
        this.folderName = name;
    }

    public ArrayList<ModuleInfo> getModules() {
        return new ArrayList<ModuleInfo>(modules);
    }

    public String getFolderName() {
        return folderName;
    }

    public ModuleInfo getModule(int index) {
        return new ModuleInfo(modules.get(index));
    }

    public void setModules(ArrayList<ModuleInfo> modules) {
        this.modules = new ArrayList<ModuleInfo>(modules);
    }

    public void setModule(ModuleInfo module, int index) {
        System.out.println(getModules().get(index));
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    private ArrayList<ModuleInfo> modules;
    private String folderName;
}

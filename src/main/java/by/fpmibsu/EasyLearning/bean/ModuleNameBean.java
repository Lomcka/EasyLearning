package by.fpmibsu.EasyLearning.bean;

import java.util.ArrayList;

public class ModuleNameBean extends Bean {
    public ModuleNameBean() {
        moduleName = "";
    }

    public ModuleNameBean(Long id, String moduleName) {
        super(id);
        if (moduleName == null) {
            throw new IllegalArgumentException("moduleName is null");
        }

        this.moduleName = moduleName;
    }

    public ModuleNameBean(ModuleNameBean moduleNameBean) {
        if (moduleNameBean == null) {
            throw new IllegalArgumentException("moduleNameBean is null");
        }

        id = moduleNameBean.getId();
        moduleName = moduleNameBean.getModuleName();
    }

    public String getModuleName() {
        return moduleName;
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

        ModuleNameBean module = (ModuleNameBean) obj;

        return id.equals(module.getId()) && moduleName.equals(module.getModuleName());
    }

    @Override
    public String toString() {
        return "ModuleBean{" +
                "id=" + id +
                ", moduleName='" + moduleName +
                '}';
    }

    private String moduleName;
}

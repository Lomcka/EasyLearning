package by.fpmibsu.EasyLearning.bean.dao_bean;

import by.fpmibsu.EasyLearning.bean.Bean;

public class ModuleDaoBean extends Bean {
    public ModuleDaoBean() {
        moduleName = "";
        ownerId = 0L;
    }

    public ModuleDaoBean(Long id, String moduleName, Long ownerId) {
        super(id);
        if (moduleName == null) {
            throw new IllegalArgumentException("moduleName is null");
        }
        if (ownerId == null) {
            throw new IllegalArgumentException("ownerId is null");
        }

        this.moduleName = moduleName;
        this.ownerId = ownerId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setModuleName(String moduleName) {
        if (moduleName == null) {
            throw new IllegalArgumentException("moduleName is null");
        }

        this.moduleName = moduleName;
    }

    public void setOwnerId(Long ownerId) {
        if (ownerId == null) {
            throw new IllegalArgumentException("ownerId is null");
        }

        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ModuleDaoBean moduleDAO = (ModuleDaoBean) obj;
        return id.equals(moduleDAO.id) && moduleName.equals(moduleDAO.moduleName) && ownerId.equals(moduleDAO.ownerId);
    }

    @Override
    public String toString() {
        return "ModuleDaoBean{" +
                "moduleName='" + moduleName + '\'' +
                ", ownerId=" + ownerId +
                ", id=" + id +
                '}';
    }

    private String moduleName;
    private Long ownerId;
}

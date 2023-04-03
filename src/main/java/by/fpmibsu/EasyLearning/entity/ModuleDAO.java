package by.fpmibsu.EasyLearning.entity;

public class ModuleDAO extends Bean {
    public ModuleDAO() {
        moduleName = "";
        ownerId = 0L;
    }

    public ModuleDAO(String moduleName, Long ownerId) {
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

        ModuleDAO moduleDAO = (ModuleDAO) obj;
        return moduleName.equals(moduleDAO.moduleName) && ownerId.equals(moduleDAO.ownerId);
    }

    private String moduleName;
    private Long ownerId;
}

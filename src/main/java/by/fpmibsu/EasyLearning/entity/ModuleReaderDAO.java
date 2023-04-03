package by.fpmibsu.EasyLearning.entity;

public class ModuleReaderDAO extends Bean {
    public ModuleReaderDAO() {
        moduleId = 0L;
        readerId = 0L;
    }

    public ModuleReaderDAO(Long moduleId, Long readerId) {
        if (moduleId == null) {
            throw new IllegalArgumentException("moduleId is null");
        }
        if (readerId == null) {
            throw new IllegalArgumentException("readerId is null");
        }

        this.moduleId = moduleId;
        this.readerId = readerId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setModuleId(Long moduleId) {
        if (moduleId == null) {
            throw new IllegalArgumentException("moduleId is null");
        }

        this.moduleId = moduleId;
    }

    public void setReaderId(Long readerId) {
        if (readerId == null) {
            throw new IllegalArgumentException("readerId is null");
        }

        this.readerId = readerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ModuleReaderDAO moduleReader = (ModuleReaderDAO) obj;
        return moduleId.equals(moduleReader.moduleId) && readerId.equals(moduleReader.readerId);
    }

    private Long moduleId;
    private Long readerId;
}

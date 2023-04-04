package by.fpmibsu.EasyLearning.bean.dao_bean;

import by.fpmibsu.EasyLearning.bean.Bean;

public class ModuleReaderDaoBean extends Bean {
    public ModuleReaderDaoBean() {
        moduleId = 0L;
        readerId = 0L;
    }

    public ModuleReaderDaoBean(Long moduleId, Long readerId) {
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

        ModuleReaderDaoBean moduleReader = (ModuleReaderDaoBean) obj;
        return moduleId.equals(moduleReader.moduleId) && readerId.equals(moduleReader.readerId);
    }

    private Long moduleId;
    private Long readerId;
}

package by.fpmibsu.EasyLearning.bean.dao_bean;

import by.fpmibsu.EasyLearning.bean.Bean;

public class FolderContentDaoBean extends Bean {
    public FolderContentDaoBean() {
        folderId = 0L;
        moduleId = 0L;
    }

    public FolderContentDaoBean(Long folderId, Long moduleId) {
        if (folderId == null) {
            throw new IllegalArgumentException("folderId is null");
        }
        if (moduleId == null) {
            throw new IllegalArgumentException("moduleId is null");
        }

        this.folderId = folderId;
        this.moduleId = moduleId;
    }

    public Long getFolderId() {
        return folderId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setFolderId(Long folderId) {
        if (folderId == null) {
            throw new IllegalArgumentException("folderId is null");
        }

        this.folderId = folderId;
    }

    public void setModuleId(Long moduleId) {
        if (moduleId == null) {
            throw new IllegalArgumentException("moduleId is null");
        }

        this.moduleId = moduleId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        FolderContentDaoBean folderContent = (FolderContentDaoBean) obj;

        return folderId.equals(folderContent.folderId) && moduleId.equals(folderContent.moduleId);
    }

    @Override
    public String toString() {
        return "FolderContentDaoBean{" +
                "folderId=" + folderId +
                ", moduleId=" + moduleId +
                '}';
    }

    private Long folderId;
    private Long moduleId;
}

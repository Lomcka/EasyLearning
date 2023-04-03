package by.fpmibsu.EasyLearning.entity;

import java.util.Objects;

public class FolderContentDAO {
    public FolderContentDAO() {
        folderId = 0L;
        moduleId = 0L;
    }

    public FolderContentDAO(Long folderId, Long moduleId) {
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

        FolderContentDAO folderContent = (FolderContentDAO) obj;

        return folderId.equals(folderContent.folderId) && moduleId.equals(folderContent.moduleId);
    }

    private Long folderId;
    private Long moduleId;
}

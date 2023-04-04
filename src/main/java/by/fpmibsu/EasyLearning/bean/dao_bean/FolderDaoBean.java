package by.fpmibsu.EasyLearning.bean.dao_bean;

import by.fpmibsu.EasyLearning.bean.Bean;

public class FolderDaoBean extends Bean {
    public FolderDaoBean() {
        folderName = "";
        ownerId = 0L;
    }

    public FolderDaoBean(Long id, String folderName, Long ownerId) {
        super(id);
        if (folderName == null) {
            throw new IllegalArgumentException("folderName is null");
        }
        if (ownerId == null) {
            throw new IllegalArgumentException("ownerId is null");
        }

        this.folderName = folderName;
        this.ownerId = ownerId;
    }

    public String getFolderName() {
        return folderName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setFolderName(String folderName) {
        if (folderName == null) {
            throw new IllegalArgumentException("folderName is null");
        }

        this.folderName = folderName;
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

        FolderDaoBean folder = (FolderDaoBean) obj;
        return id.equals(folder.id) && folderName.equals(folder.folderName) && ownerId.equals(folder.ownerId);
    }

    @Override
    public String toString() {
        return "FolderDaoBean{" +
                "id=" + id +
                ", folderName='" + folderName + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }

    private String folderName;
    private Long ownerId;
}

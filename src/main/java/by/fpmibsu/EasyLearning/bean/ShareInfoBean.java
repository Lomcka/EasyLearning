package by.fpmibsu.EasyLearning.bean;

public class ShareInfoBean extends Bean {
    public ShareInfoBean() {
        login = "";
        keyWord = "";
    }

    public ShareInfoBean(Long id, String login, String keyWord) {
        super(id);
        if (login == null) {
            throw new IllegalArgumentException("login is null");
        }
        if (keyWord == null) {
            throw new IllegalArgumentException("keyWord is null");
        }

        this.login = login;
        this.keyWord = keyWord;
    }

    public String getLogin() {
        return login;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setLogin(String login) {
        if (login == null) {
            throw new IllegalArgumentException("login is null");
        }

        this.login = login;
    }

    public void setKeyWord(String keyWord) {
        if (keyWord == null) {
            throw new IllegalArgumentException("keyWord is null");
        }

        this.keyWord = keyWord;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ShareInfoBean shareInfo = (ShareInfoBean) obj;

        return id.equals(shareInfo.getId()) &&
                login.equals(shareInfo.getLogin()) && keyWord.equals(shareInfo.getKeyWord());
    }

    private String login;
    private String keyWord;
}

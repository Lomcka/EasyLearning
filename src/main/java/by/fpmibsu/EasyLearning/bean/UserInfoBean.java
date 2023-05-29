package by.fpmibsu.EasyLearning.bean;

public class UserInfoBean extends Bean {
    public UserInfoBean() {
        login = "";
        password = "";
        keyWord = "";
    }

    public UserInfoBean(Long id, String login, String password, String keyWord) {
        super(id);
        if (login == null) {
            throw new IllegalArgumentException("login is null");
        }

        if (password == null) {
            throw new IllegalArgumentException("password is null");
        }

        if (keyWord == null) {
            throw new IllegalArgumentException("keyWord is null");
        }

        this.login = login;
        this.password = password;
        this.keyWord = keyWord;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("password is null");
        }

        this.password = password;
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

        UserInfoBean userInfo = (UserInfoBean) obj;

        return id.equals(userInfo.getId()) && login.equals(userInfo.login) &&
                password.equals(userInfo.password) && keyWord.equals(userInfo.keyWord);
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }

    private String login;
    private String password;
    private String keyWord;
}

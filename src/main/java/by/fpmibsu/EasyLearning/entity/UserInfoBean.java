package by.fpmibsu.EasyLearning.entity;

public class UserInfoBean {
    public UserInfoBean() {
        login = "";
        password = "";
        keyWord = "";
    }

    public UserInfoBean(String login, String password, String keyWord) {
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
            throw new IllegalArgumentException("login points to null");
        }

        this.login = login;
    }

    public void setPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("password points to null");
        }

        this.password = password;
    }

    public void setKeyWord(String keyWord) {
        if (keyWord == null) {
            throw new IllegalArgumentException("kewWord points to null");
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

        return login.equals(userInfo.login) && password.equals(userInfo.password) && keyWord.equals(userInfo.keyWord);
    }

    private String login;
    private String password;
    private String keyWord;
}

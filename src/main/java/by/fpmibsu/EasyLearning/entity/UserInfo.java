package by.fpmibsu.EasyLearning.entity;

public class UserInfo {
    public UserInfo() {
        login = "";
        password = "";
        keyWord = "";
    }

    public UserInfo(String login, String password, String keyWord) {
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
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKeyWord(String keyWord) {
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

        UserInfo info = (UserInfo) obj;

        return login.equals(info.login) && password.equals(info.password) && keyWord.equals(info.keyWord);
    }

    private String login;
    private String password;
    private String keyWord;
}

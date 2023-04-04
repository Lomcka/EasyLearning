package by.fpmibsu.EasyLearning.bean.dao_bean;

import by.fpmibsu.EasyLearning.bean.Bean;

public class UserDaoBean extends Bean {
    public UserDaoBean() {
        login = "";
        keyWord = "";
    }

    public UserDaoBean(Long id, String login, String keyWord) {
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
        this.login = login;
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

        UserDaoBean user = (UserDaoBean) obj;

        return id.equals(user.getId()) &&
                login.equals(user.getLogin()) && keyWord.equals(user.getKeyWord());
    }

    @Override
    public String toString() {
        return "UserDaoBean{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }

    private String login;
    private String keyWord;
}

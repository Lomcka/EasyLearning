package by.fpmibsu.EasyLearning.entity;

public class UserDAO extends Bean {
    public UserDAO() {
        login = "";
        keyWord = "";
    }

    public UserDAO(Long id, String login, String keyWord) {
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

        UserDAO user = (UserDAO) obj;

        return id.equals(user.getId()) &&
                login.equals(user.getLogin()) && keyWord.equals(user.getKeyWord());
    }

    private String login;
    private String keyWord;
}

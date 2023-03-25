package by.fpmibsu.EasyLearning.entity;

public class RegistrationInfoBean extends Bean {
    public RegistrationInfoBean() {
        login = "";
        password = "";
        keyWord = "";
    }

    public RegistrationInfoBean(Long id, String login, String password, String keyWord) {
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
            throw new IllegalArgumentException("kewWord is null");
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

        RegistrationInfoBean regInfo = (RegistrationInfoBean) obj;

        return id.equals(regInfo.getId()) && login.equals(regInfo.login) &&
                password.equals(regInfo.password) && keyWord.equals(regInfo.keyWord);
    }

    private String login;
    private String password;
    private String keyWord;
}

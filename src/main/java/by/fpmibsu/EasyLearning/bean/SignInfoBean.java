package by.fpmibsu.EasyLearning.bean;

public class SignInfoBean extends Bean {
    public SignInfoBean() {
        super();
        login = "";
        password = "";
    }

    public SignInfoBean(Long id, String login, String password) {
        super(id);
        if (login == null) {
            throw new IllegalArgumentException("login is null");
        }

        if (password == null) {
            throw new IllegalArgumentException("password is null");
        }

        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SignInfoBean signInfo = (SignInfoBean) obj;

        return id.equals(signInfo.getId()) &&
                login.equals(signInfo.getLogin()) && password.equals(signInfo.getPassword());
    }

    @Override
    public String toString() {
        return "SignInfoBean{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    private String login;
    private String password;
}

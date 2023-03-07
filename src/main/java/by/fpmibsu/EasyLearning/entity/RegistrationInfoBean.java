package by.fpmibsu.EasyLearning.entity;

public class RegistrationInfoBean {
    public RegistrationInfoBean() {
        login = "";
        password = "";
    }

    public RegistrationInfoBean(String login, String password) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        RegistrationInfoBean regInfo = (RegistrationInfoBean) obj;

        return login.equals(regInfo.getLogin()) && password.equals(regInfo.getPassword());
    }

    private String login;
    private String password;
}

package by.fpmibsu.EasyLearning.entity;

public class RegistrationInfo {
    public RegistrationInfo() {
        login = "";
        password = "";
    }

    public RegistrationInfo(String login, String password) {
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
        this.login = login;
    }

    public void setPassword(String password) {
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

        UserInfo info = (UserInfo) obj;

        return login.equals(info.getLogin()) && password.equals(info.getPassword());
    }

    private String login;
    private String password;
}

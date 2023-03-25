package by.fpmibsu.EasyLearning.entity;

public class UserInfoBean extends Bean {
    public UserInfoBean() {
        super();
        login = "";
        password = "";
    }

    public UserInfoBean(Long id, String login, String password) {
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

        UserInfoBean userInfo = (UserInfoBean) obj;

        return id.equals(userInfo.getId()) &&
                login.equals(userInfo.getLogin()) && password.equals(userInfo.getPassword());
    }

    private String login;
    private String password;
}

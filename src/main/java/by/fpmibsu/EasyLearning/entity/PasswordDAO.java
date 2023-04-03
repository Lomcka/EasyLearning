package by.fpmibsu.EasyLearning.entity;

public class PasswordDAO extends Bean {
    public PasswordDAO() {
        password = "";
    }

    public PasswordDAO(String password) {
        super();
        if (password == null) {
            throw new IllegalArgumentException("password is null");
        }

        this.password = password;
    }

    public String getPassword() {
        return password;
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

        PasswordDAO passwordBean = (PasswordDAO) obj;
        return password.equals(passwordBean.password);
    }

    private String password;
}

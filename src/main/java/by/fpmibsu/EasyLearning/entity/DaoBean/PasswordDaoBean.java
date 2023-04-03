package by.fpmibsu.EasyLearning.entity.DaoBean;

import by.fpmibsu.EasyLearning.entity.Bean;

public class PasswordDaoBean extends Bean {
    public PasswordDaoBean() {
        password = "";
    }

    public PasswordDaoBean(String password) {
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

        PasswordDaoBean passwordBean = (PasswordDaoBean) obj;
        return password.equals(passwordBean.password);
    }

    private String password;
}

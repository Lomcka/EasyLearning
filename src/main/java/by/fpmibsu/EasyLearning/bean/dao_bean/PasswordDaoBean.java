package by.fpmibsu.EasyLearning.bean.dao_bean;

import java.util.Objects;

import by.fpmibsu.EasyLearning.bean.Bean;

public class PasswordDaoBean extends Bean {
    public PasswordDaoBean() {
        password = "";
    }

    public PasswordDaoBean(Long userId, String password) {
        super();
        if (userId == null) {
            throw new IllegalArgumentException("userId is null");
        }
        if (password == null) {
            throw new IllegalArgumentException("password is null");
        }

        this.userId = userId;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId is null");
        }

        this.userId = userId;
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
        return userId.equals(passwordBean.userId) && password.equals(passwordBean.password);
    }

    private Long userId;
    private String password;
}

package test.fpmibsu.EasyLearning.service;

import by.fpmibsu.EasyLearning.bean.UserInfoBean;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.UserInfoService;
import by.fpmibsu.EasyLearning.service.impl.UserInfoServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserInfoServiceTest {
    private UserInfoService service;

    @BeforeClass
    public void init() {
        service = new UserInfoServiceImpl();
    }

    @DataProvider(name = "findByIdPositive")
    public Object[][] createFindByIdPositiveData() {
        return new Object[][] {
                {1L, new UserInfoBean(1L, "vanya", "1234", "hi_world")},
                {2L, new UserInfoBean(2L, "sophia", "1111", "mama_mia")},
                {3L, new UserInfoBean(3L, "roman", "noob228", "my_godness")}
        };
    }

    @Test(dataProvider = "findByIdPositive")
    public void findByIdTestPositive(Long id, UserInfoBean expected) {
        try {
            var userInfo = service.findById(id);
            assertTrue(userInfo.isPresent());
            assertEquals(userInfo.get(), expected);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByIdNegative")
    public Object[] createFindByIdNegativeData() {
            return new Object[] {42L, -42L, 424242L};
    }

    @Test(dataProvider = "findByIdNegative")
    public void findByIdTestNegative(Long id) {
        try {
            var userInfo = service.findById(id);
            assertTrue(userInfo.isEmpty());
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByLoginPositive")
    public Object[][] createFindByLoginPositiveData() {
        return new Object[][] {
                {"vanya", new UserInfoBean(1L, "vanya", "1234", "hi_world")},
                {"sophia", new UserInfoBean(2L, "sophia", "1111", "mama_mia")},
                {"roman", new UserInfoBean(3L, "roman", "noob228", "my_godness")}
        };
    }

    @Test(dataProvider = "findByLoginPositive")
    public void findByLoginTestPositive(String login, UserInfoBean expected) {
        try {
            var userInfo = service.findByLogin(login);
            assertTrue(userInfo.isPresent());
            assertEquals(userInfo.get(), expected);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByLoginNegative")
    public Object[] createFindByLoginNegativeData() {
        return new Object[]{"Aboba", "Shish", "Kek", "lol"};
    }

    @Test(dataProvider = "findByLoginNegative")
    public void findByLoginTestNegative(String login) {
        try {
            var userInfo = service.findByLogin(login);
            assertTrue(userInfo.isEmpty());
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "updatePasswordPositive")
    public Object[][] createUpdatePasswordPositiveData() {
        return new Object[][] {
                {1L, "4321", new UserInfoBean(1L, "vanya", "4321", "hi_world")},
                {2L, "2222", new UserInfoBean(2L, "sophia", "2222", "mama_mia")},
                {3L, "I am god", new UserInfoBean(3L, "roman", "I am god", "my_godness")}
        };
    }

    @Test(dataProvider = "updatePasswordPositive")
    public void updatePasswordTestPositive(Long id, String newPassword, UserInfoBean expected) {
        try {
            var userInfo = service.findById(id);
            assertTrue(userInfo.isPresent());
            String oldPassword = userInfo.get().getPassword();

            service.updatePassword(id, newPassword);

            userInfo = service.findById(id);
            assertTrue(userInfo.isPresent());
            assertEquals(userInfo.get(), expected);

            service.updatePassword(id, oldPassword);
        } catch (ServiceException e) {
            fail();
        }
    }

    @AfterClass
    public void destroy() {
        service = null;
    }
}

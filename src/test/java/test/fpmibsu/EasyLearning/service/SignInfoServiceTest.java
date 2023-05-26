package test.fpmibsu.EasyLearning.service;

import by.fpmibsu.EasyLearning.bean.SignInfoBean;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.SignInfoService;
import by.fpmibsu.EasyLearning.service.impl.SignInfoServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.FileAssert.fail;

public class SignInfoServiceTest {
    private SignInfoService service;

    @BeforeClass
    public void init() {
        service = new SignInfoServiceImpl();
    }

    @DataProvider(name = "findByIdPositive")
    public Object[][] createFindByIdPositiveData() {
        return new Object[][] {
                {1L, new SignInfoBean(1L, "vanya", "1234")},
                {2L, new SignInfoBean(2L, "sophia", "1111")},
                {3L, new SignInfoBean(3L, "roman", "noob228")}
        };
    }

    @Test(dataProvider = "findByIdPositive")
    public void findByIdTestPositive(Long id, SignInfoBean expected) {
        try {
            var signInfo = service.findById(id);
            assertTrue(signInfo.isPresent());
            assertEquals(signInfo.get(), expected);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByIdNegative")
    public Object[] createFindByIdNegativeData() {
        return new Object[] {42L, 11L, -98L, 6546L, 228L};
    }

    @Test(dataProvider = "findByIdNegative")
    public void findByIdTestNegative(Long id) {
        try {
            var signInfo = service.findById(id);
            assertTrue(signInfo.isEmpty());
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByLoginPositive")
    public Object[][] createFindLoginIdPositiveData() {
        return new Object[][] {
                {"vanya", new SignInfoBean(1L, "vanya", "1234")},
                {"sophia", new SignInfoBean(2L, "sophia", "1111")},
                {"roman", new SignInfoBean(3L, "roman", "noob228")}
        };
    }

    @Test(dataProvider = "findByLoginPositive")
    public void findByLoginTestPositive(String login, SignInfoBean expected) {
        try {
            var signInfo = service.findByLogin(login);
            assertTrue(signInfo.isPresent());
            assertEquals(signInfo.get(), expected);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByLoginNegative")
    public Object[] createFindByLoginNegativeData() {
        return new Object[] {"Dima", "Iren", "Polyushka_kost", "Amogus"};
    }

    @Test(dataProvider = "findByLoginNegative")
    public void findByLoginTestNegative(String login) {
        try {
            var signInfo = service.findByLogin(login);
            assertTrue(signInfo.isEmpty());
        } catch (ServiceException e) {
            fail();
        }
    }

    @AfterClass
    public void destroy() {
        service = null;
    }
}

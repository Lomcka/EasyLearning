package test.fpmibsu.EasyLearning.service;

import by.fpmibsu.EasyLearning.bean.ShareInfoBean;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.ShareInfoService;
import by.fpmibsu.EasyLearning.service.impl.ShareInfoServiceImpl;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class ShareInfoServiceTest {
    private ShareInfoService service;

    @BeforeClass
    public void init() {
        service = new ShareInfoServiceImpl();
    }

    @DataProvider(name = "findByIdPositive")
    public Object[][] createFindByIdPositiveData() {
        return new Object[][] {
                {1L, new ShareInfoBean(1L, "vanya", "hi_world")},
                {2L, new ShareInfoBean(2L, "sophia", "mama_mia")},
                {3L, new ShareInfoBean(3L, "roman", "my_godness")}
        };
    }

    @Test(dataProvider = "findByIdPositive")
    public void findByIdTestPositive(long id, ShareInfoBean expectedBean) {
        try {
            var shareInfo = service.findById(id);
            assertTrue(shareInfo.isPresent());
            assertEquals(shareInfo.get(), expectedBean);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByIdNegative")
    public Object[] createFindByIdNegativeData() {
        return new Object[] {-5L, 5L, 1000L};
    }

    @Test(dataProvider = "findByIdNegative")
    public void findByIdTestNegative(long id) {
        try {
            var shareInfo = service.findById(id);
            assertTrue(shareInfo.isEmpty());
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByLoginPositive")
    public Object[][] createFindByLoginTestPositiveData() {
        return new Object[][] {
                {"vanya", new ShareInfoBean(1L, "vanya", "hi_world")},
                {"sophia", new ShareInfoBean(2L, "sophia", "mama_mia")},
                {"roman", new ShareInfoBean(3L, "roman", "my_godness")}
        };
    }

    @Test(dataProvider = "findByLoginPositive")
    public void findByLoginTestPositive(String login, ShareInfoBean expectedBean) {
        try {
            var shareInfo = service.findByLogin(login);
            assertTrue(shareInfo.isPresent());
            assertEquals(shareInfo.get(), expectedBean);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByLoginNegative")
    public Object[] createFindByLoginNegativeData() {
        return new Object[] {"aboba", "abeba", "aaaaa", "Dimon"};
    }

    @Test(dataProvider = "findByLoginNegative")
    public void findByLoginTestNegative(String login) {
        try {
            var shareInfo = service.findByLogin(login);
            assertTrue(shareInfo.isEmpty());
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "updateLoginPositive")
    public Object[][] createUpdateLoginPositiveData() {
        return new Object[][] {
                {1L, "Vanka", new ShareInfoBean(1L, "Vanka", "hi_world")},
                {2L, "Mama", new ShareInfoBean(2L, "Mama", "mama_mia")},
                {3L, "Aboba", new ShareInfoBean(3L, "Aboba", "my_godness")}
        };
    }

    @Test(dataProvider = "updateLoginPositive")
    public void updateLoginTestPositive(Long id, String newLogin, ShareInfoBean expectedBean) {
        try {
            var shareInfo = service.findById(id);
            assertTrue(shareInfo.isPresent());

            String oldLogin = shareInfo.get().getLogin();

            service.updateLogin(id, newLogin);

            shareInfo = service.findById(id);
            assertTrue(shareInfo.isPresent());
            assertEquals(shareInfo.get(), expectedBean);

            service.updateLogin(id, oldLogin);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "updateKeyWordPositive")
    public Object[][] createUpdateKeyWordPositiveData() {
        return new Object[][] {
                {1L, "bye bye", new ShareInfoBean(1L, "vanya", "bye bye")},
                {2L, "mia_mama", new ShareInfoBean(2L, "sophia", "mia_mama")},
                {3L, "the evil", new ShareInfoBean(3L, "roman", "the evil")}
        };
    }

    @Test(dataProvider = "updateKeyWordPositive")
    public void updateKeywordTestPositive(Long id, String newKeyWord, ShareInfoBean expectedBean) {
        try {
            var shareInfo = service.findById(id);
            assertTrue(shareInfo.isPresent());
            String oldKeyWord = shareInfo.get().getKeyWord();

            service.updateKeyword(id, newKeyWord);

            shareInfo = service.findById(id);
            assertTrue(shareInfo.isPresent());
            assertEquals(shareInfo.get(), expectedBean);

            service.updateKeyword(id, oldKeyWord);
        } catch (ServiceException e) {
            fail();
        }
    }

    @AfterClass
    public void destroy() {
        service = null;
    }
}

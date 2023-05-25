package test.fpmibsu.EasyLearning.service;

import by.fpmibsu.EasyLearning.bean.ShareInfoBean;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.ShareInfoService;
import by.fpmibsu.EasyLearning.service.impl.ShareInfoServiceImpl;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShareInfoServiceTest {
    private ShareInfoService service;

    @BeforeGroups(groups = "share_info_service")
    public void init() {
        service = new ShareInfoServiceImpl();
    }

    @Test(groups = "share_info_service",
          dataProvider = "findByIdPositive")
    public void findByIdTestPositive(long id, ShareInfoBean expectedBean) {
        try {
            var shareInfo = service.findById(id);
            assertTrue(shareInfo.isPresent());
            assertEquals(shareInfo.get(), expectedBean);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test(groups = "share_info_service",
          dataProvider = "findByIdNegative")
    public void findByIdTestNegative(long id) {
        try {
            var shareInfo = service.findById(id);
            assertTrue(shareInfo.isEmpty());
        } catch (ServiceException e) {
            fail();
        }
    }

    @AfterGroups(groups = "share_info_service")
    public void destroy() {
        service = null;
    }

    @DataProvider(name = "findByIdPositive")
    public Object[][] createFindByIdPositiveData() {
        return new Object[][] {
                {1L, new ShareInfoBean(1L, "vanya", "hi_world")},
                {2L, new ShareInfoBean(2L, "sophia", "mama_mia")},
                {3L, new ShareInfoBean(3L, "roman", "my_godness")}
        };
    }

    @DataProvider(name = "findByIdNegative")
    public Object[] createFindByIdNegativeData() {
        return new Object[] {-5L, 5L, 1000L};
    }
}

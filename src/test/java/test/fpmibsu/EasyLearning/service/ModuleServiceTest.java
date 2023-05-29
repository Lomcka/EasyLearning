package test.fpmibsu.EasyLearning.service;

import by.fpmibsu.EasyLearning.bean.CardBean;
import by.fpmibsu.EasyLearning.bean.ModuleBean;
import by.fpmibsu.EasyLearning.exception.ServiceException;
import by.fpmibsu.EasyLearning.service.ModuleService;
import by.fpmibsu.EasyLearning.service.impl.ModuleServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.testng.AssertJUnit.*;
import static org.testng.FileAssert.fail;

public class ModuleServiceTest {
    private ModuleService service;

    @BeforeClass
    public void Init() {
        service = new ModuleServiceImpl();
    }

    @DataProvider(name = "findByIdPositive")
    public Object[][] createFindByIdPositiveData() {
        ArrayList<CardBean> firstModuleContents = new ArrayList<>();
        firstModuleContents.add(new CardBean(1L, 1L, "Trash", "Can"));
        firstModuleContents.add(new CardBean(2L, 1L, "Man", "Woman"));

        ArrayList<CardBean> secondModuleContents = new ArrayList<>();
        secondModuleContents.add(new CardBean(3L, 2L, "Serial", "Killer"));

        ArrayList<CardBean> thirdModuleContents = new ArrayList<>();
        thirdModuleContents.add(new CardBean(4L, 3L, "Mom", "Dad"));

        return new Object[][] {
                {1L, new ModuleBean(1L, "Eng", firstModuleContents)},
                {2L, new ModuleBean(2L, "Ger", secondModuleContents)},
                {3L, new ModuleBean(3L, "Bel", thirdModuleContents)}
        };
    }

    @Test(dataProvider = "findByIdPositive")
    public void findByIdTestPositive(Long id, ModuleBean expectedBean) {
        try {
            var module = service.findById(id);
            assertTrue(module.isPresent());
            assertEquals(module.get(), expectedBean);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByIdNegative")
    public Object[] createFindByIdNegativeData() {
        return new Object[] {42L, 4224L, 666L, -999L};
    }

    @Test(dataProvider = "findByIdNegative")
    public void findByIdTestNegative(Long id) {
        try {
            var module = service.findById(id);
            assertTrue(module.isEmpty());
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByNamePositive")
    public Object[] createFindByNamePositiveData() {
        ArrayList<CardBean> firstModuleContents = new ArrayList<>();
        firstModuleContents.add(new CardBean(1L, 1L, "Trash", "Can"));
        firstModuleContents.add(new CardBean(2L, 1L, "Man", "Woman"));

        ArrayList<CardBean> secondModuleContents = new ArrayList<>();
        secondModuleContents.add(new CardBean(3L, 2L, "Serial", "Killer"));

        ArrayList<CardBean> thirdModuleContents = new ArrayList<>();
        thirdModuleContents.add(new CardBean(4L, 3L, "Mom", "Dad"));

        return new Object[][] {
                {"Eng", new ModuleBean(1L, "Eng", firstModuleContents)},
                {"Ger", new ModuleBean(2L, "Ger", secondModuleContents)},
                {"Bel", new ModuleBean(3L, "Bel", thirdModuleContents)}
        };
    }

    @Test(dataProvider = "findByNamePositive")
    public void findByNameTestPositive(String Name, ModuleBean expected) {
        try {
            var module = service.findByName(Name);
            assertTrue(module.isPresent());
            assertEquals(module.get(), expected);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByNameNegative")
    public Object[] createFindByNameNegativeData() {
        return new Object[] {"Rus", "Arabic", "Belg", "Italian", "Computer Science"};
    }

    @Test(dataProvider = "findByNameNegative")
    public void findByNameTestNegative(String Name) {
        try {
            var module = service.findByName(Name);
            assertTrue(module.isEmpty());
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByOwnerIdPositive")
    public Object[][] createFindByOwnerIdPositiveData() {
        ArrayList<String> firstOwnerModuleNames = new ArrayList<>();
        firstOwnerModuleNames.add("Eng");

        ArrayList<String> secondOwnerModuleNames = new ArrayList<>();
        secondOwnerModuleNames.add("Ger");

        ArrayList<String> thirdOwnerModuleNames = new ArrayList<>();
        thirdOwnerModuleNames.add("Bel");

        return new Object[][] {
                {1L, firstOwnerModuleNames},
                {2L, secondOwnerModuleNames},
                {3L, thirdOwnerModuleNames}
        };
    }

    @Test(dataProvider = "findByOwnerIdPositive")
    public void findByOwnerIdTestPositive(Long ownerId, ArrayList<String> expectedNames) {
        try {
            var moduleNames = service.findByOwnerId(ownerId);
            assertEquals(moduleNames, expectedNames);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByOwnerIdNegative")
    public Object[] createFindByOwnerIdNegativeData() {
        return new Object[] {42L, 228L, 1337L, -1L, -24L};
    }

    @Test(dataProvider = "findByOwnerIdNegative")
    public void findByOwnerIdTestNegative(Long ownerId) {
        try {
            var moduleNames = service.findByOwnerId(ownerId);
            assertTrue(moduleNames.isEmpty());
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByReaderIdPositive")
    public Object[][] createFindByReaderIdPositiveData() {
        ArrayList<String> firstReaderModuleNames = new ArrayList<>();
        firstReaderModuleNames.add("Eng");

        ArrayList<String> secondReaderModuleNames = new ArrayList<>();
        secondReaderModuleNames.add("Ger");

        ArrayList<String> thirdReaderModuleNames = new ArrayList<>();
        thirdReaderModuleNames.add("Bel");

        return new Object[][] {
                {1L, firstReaderModuleNames},
                {2L, secondReaderModuleNames},
                {3L, thirdReaderModuleNames}
        };
    }

    @Test(dataProvider = "findByReaderIdPositive")
    public void findByReaderIdTestPositive(Long readerId, ArrayList<String> expectedNames) {
        try {
            var moduleNames = service.findByReaderId(readerId);
            assertEquals(moduleNames, expectedNames);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "findByReaderIdNegative")
    public Object[] createFindByReaderIdNegativeData() {
        return new Object[] {42L, -42L, 228L};
    }

    @Test(dataProvider = "findByReaderIdNegative")
    public void findByReaderIdTestNegative(Long readerId) {
        try {
            var moduleNames = service.findByReaderId(readerId);
            assertTrue(moduleNames.isEmpty());
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "updateModuleName")
    public Object[][] createUpdateModuleNameData() {
        ArrayList<CardBean> firstModuleContents = new ArrayList<>();
        firstModuleContents.add(new CardBean(1L, 1L, "Trash", "Can"));
        firstModuleContents.add(new CardBean(2L, 1L, "Man", "Woman"));

        ArrayList<CardBean> secondModuleContents = new ArrayList<>();
        secondModuleContents.add(new CardBean(3L, 2L, "Serial", "Killer"));

        ArrayList<CardBean> thirdModuleContents = new ArrayList<>();
        thirdModuleContents.add(new CardBean(4L, 3L, "Mom", "Dad"));

        return new Object[][] {
                {1L, "Rus", new ModuleBean(1L, "Rus", firstModuleContents)},
                {2L, "Science", new ModuleBean(2L, "Science", secondModuleContents)},
                {3L, "Aboba", new ModuleBean(3L, "Aboba", thirdModuleContents)}
        };
    }

    @Test(dataProvider = "updateModuleName")
    public void updateModuleNameTest(Long moduleId, String newName, ModuleBean expectedBean) {
        try {
            var module = service.findById(moduleId);
            assertTrue(module.isPresent());
            String oldName = module.get().getModuleName();

            service.updateModuleName(moduleId, newName);

            module = service.findById(moduleId);
            assertTrue(module.isPresent());
            assertEquals(module.get(), expectedBean);

            service.updateModuleName(moduleId, oldName);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "addReaderTest")
    public Object[][] createAddReaderTestData() {
        ArrayList<String> firstReaderModuleNames = new ArrayList<>();
        firstReaderModuleNames.add("Ger");
        firstReaderModuleNames.add("Eng");

        ArrayList<String> secondReaderModuleNames = new ArrayList<>();
        secondReaderModuleNames.add("Ger");
        secondReaderModuleNames.add("Eng");

        ArrayList<String> thirdReaderModuleNames = new ArrayList<>();
        thirdReaderModuleNames.add("Bel");
        thirdReaderModuleNames.add("Ger");

        return new Object[][] {
                {2L, 1L, firstReaderModuleNames},
                {1L, 2L, secondReaderModuleNames},
                {2L, 3L, thirdReaderModuleNames}
        };
    }

    @Test(enabled = false, dataProvider = "addReaderTest")
    public void addReaderTest(Long moduleId, Long readerId, ArrayList<String> moduleNames) {
        try {
            service.addReader(moduleId, readerId);
            var newModuleNames = service.findByReaderId(readerId);

            Collections.sort(moduleNames);
            Collections.sort(newModuleNames);

            assertEquals(newModuleNames, moduleNames);
        } catch (ServiceException e) {
            fail();
        }
    }

    @DataProvider(name = "updateCardTest")
    public Object[][] createUpdateCardTestData() {
        ArrayList<CardBean> firstModuleContents = new ArrayList<>();
        firstModuleContents.add(new CardBean(1L, 1L, "Wow", "Wow"));
        firstModuleContents.add(new CardBean(2L, 1L, "Man", "Woman"));

        ArrayList<CardBean> secondModuleContents = new ArrayList<>();
        secondModuleContents.add(new CardBean(3L, 2L, "Sus", "Quit"));

        return new Object[][] {
                {1L, 1L, new CardBean(1L, 1L, "Wow", "Wow"),
                    new ModuleBean(1L, "Eng", firstModuleContents)},
                {2L, 3L, new CardBean(3L, 2L, "Sus", "Quit"),
                        new ModuleBean(2L, "Ger", secondModuleContents)}
        };
    }

    @Test(enabled = false, dataProvider = "updateCardTest")
    public void updateCardTest(Long moduleId, Long cardId, CardBean newCardBean, ModuleBean expectedModuleBean) {
        try {
            service.updateCard(moduleId, cardId, newCardBean);
            var newCardBeans = service.findById(moduleId);
            assertTrue(newCardBeans.isPresent());
            assertEquals(newCardBeans.get(), expectedModuleBean);
        } catch (ServiceException e) {
            fail();
        }
    }

    @AfterClass
    public void destroy() {
        service = null;
    }
}

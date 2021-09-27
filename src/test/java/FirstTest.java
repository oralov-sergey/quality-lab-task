import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


public class FirstTest {
    final int EXPECTED_NUMBER_IS_ONE = 1;
    final int EXPECTED_NUMBER_IS_FOUR = 4;
    final int EXPECTED_NUMBER_IS_FIVE = 5;
    final int SUM = 2 + 2;

    @BeforeClass
    static void beforeAll() {
        System.out.println("FirstTest class started");
    }

    @BeforeMethod
    void beforeEach() {
        System.out.println("Test start");
    }

    @AfterMethod
    void afterEach() {
        System.out.println("Test finished");
    }

    @AfterClass
    static void afterAll() {
        System.out.println("All tests in FirstTest finished");
    }

    @Test
    void myTest() {
        System.out.println("My first autotest running");
        Assert.assertEquals(EXPECTED_NUMBER_IS_ONE, 1 / 0);
    }

    @Test
    public void test1() {
        System.out.println("Test №1");
        Assert.assertEquals(EXPECTED_NUMBER_IS_FOUR, SUM);
    }

    @Test
    public void test2() {
        System.out.println("Test №2");
        Assert.assertEquals(EXPECTED_NUMBER_IS_FIVE, SUM);
    }

    @Test
    public void test3() {
        System.out.println("Test №3");
        Assert.assertTrue(SUM == EXPECTED_NUMBER_IS_FOUR);
    }

    @Test
    public void test4() {
        System.out.println("Test №4");
        Assert.assertTrue(SUM == EXPECTED_NUMBER_IS_FIVE);
    }

    @Test
    public void test5() {
        SoftAssert soft = new SoftAssert();
        System.out.println("Test №5");
        soft.assertEquals(EXPECTED_NUMBER_IS_FOUR, SUM);
        soft.assertEquals(EXPECTED_NUMBER_IS_FIVE, SUM);
        soft.assertTrue(SUM == EXPECTED_NUMBER_IS_FOUR, "true");
        soft.assertTrue(SUM == EXPECTED_NUMBER_IS_FIVE, "false");
        soft.assertAll();
    }
}



import autotests.TestBase;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class FirstTest {
    final int EXPECTED_NUMBER_IS_ONE = 1;
    final int EXPECTED_NUMBER_IS_FOUR = 4;
    final int EXPECTED_NUMBER_IS_FIVE = 5;
    final int SUM = 2 + 2;

    @BeforeAll
    static void beforeAll(){
        System.out.println("FirstTest class started");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("Test start");
    }

    @AfterEach
    void afterEach(){
        System.out.println("Test finished");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("All tests in FirstTest finished");
    }

        @Test
        void myTest () {
        System.out.println("My first autotest running");
        assertEquals(EXPECTED_NUMBER_IS_ONE, 1 / 0);
    }

        @Test
        void test1 () {
        System.out.println("Test №1");
        assertEquals(EXPECTED_NUMBER_IS_FOUR, SUM);
    }

        @Test
        void test2 () {
        System.out.println("Test №2");
        assertEquals(EXPECTED_NUMBER_IS_FIVE, SUM);
    }

        @Test
        void test3 () {
        System.out.println("Test №3");
        assertTrue(SUM == EXPECTED_NUMBER_IS_FOUR);
    }

        @Test
        void test4 () {
        System.out.println("Test №4");
        assertTrue(SUM == EXPECTED_NUMBER_IS_FIVE);
    }

        @Test
        void test5 () {
        System.out.println("Test №5");
        assertAll("comparisons",
                () -> assertEquals(EXPECTED_NUMBER_IS_FOUR, SUM),
                () -> assertEquals(EXPECTED_NUMBER_IS_FIVE, SUM),
                () -> assertTrue(SUM == EXPECTED_NUMBER_IS_FOUR),
                () -> assertTrue(SUM == EXPECTED_NUMBER_IS_FIVE)
        );
    }
    }



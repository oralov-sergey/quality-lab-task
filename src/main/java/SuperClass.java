import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class SuperClass {
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

}

package API.BaseTest;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public static void setUp(){
        System.out.println("=== Запуск тестового комплектка ===");
    }
    @AfterSuite
    public static void getUp(){
        System.out.println("=== Тестовый комплект закончен ===");
    }
}

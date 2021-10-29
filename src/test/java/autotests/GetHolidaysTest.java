package autotests;

import api.HolidayItem;
import core.TestBase;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static api.Constants.RunVariable.port;
import static api.Constants.RunVariable.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.startsWith;

@Listeners({core.TestListener.class})
public class GetHolidaysTest extends TestBase {

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = server;
        RestAssured.port = port;
        RestAssured.basePath = path;
        RequestSpecBuilder keyParameter = new RequestSpecBuilder();
        keyParameter.addParam("key", key);
        RestAssured.requestSpecification = keyParameter.build();

        ResponseSpecBuilder responseValidations = new ResponseSpecBuilder();
        responseValidations.expectStatusCode(200);
        responseValidations.expectBody("response.message.type",
                Matchers.not(Matchers.hasItem("error")));
        RestAssured.responseSpecification = responseValidations.build();
    }

    @Description("Тест API. Проверка календаря за текущий год без ввода параметров")
    @Test
    public void withoutParameters() {

        given().log().all()
                .when()
                .get("/Calendar/GetHolidays")
                .then()
                .spec(responseSpecification)
                .body("response.items.date",
                        everyItem(startsWith(LocalDate.now().
                                format(DateTimeFormatter.ofPattern("yyyy"))))).log().body();

    }

    @Description("Тест API. Проверка календаря за указанный год. Параметр: '2019'")
    @Test
    public void parameterYear() {
        given().queryParam("year", "2019").log().all()
                .when()
                .spec(requestSpecification)
                .when()
                .get("/Calendar/GetHolidays")
                .then()
                .spec(responseSpecification)
                .body("response.items.date", everyItem(startsWith("2019"))).log().body();
    }

    @Description("Тест API. Проверка календаря на сокращенные дни. Параметр: 'short_day'")
    @Test
    public void parameterShortDayType() {
        JsonPath jsonPath = JsonPath.given(given().queryParam("day_type", "short_day").log().all()
                .spec(requestSpecification)
                .when()
                .get("/Calendar/GetHolidays")
                .then()
                .spec(responseSpecification)
                .body("response.items.type", everyItem(startsWith("short_day")))
                .extract().body().jsonPath().prettyPrint());

        List<HolidayItem> items =
                jsonPath.getList("response.items", HolidayItem.class);
        boolean DAY_TYPE = items.stream().allMatch((x) -> Objects.equals(x.getType(), "short_day"));
        Assert.assertTrue(DAY_TYPE, "AssertionFailedError: There are no days with type 'short_day'");
    }

    @Description("Тест API. Проверка календаря на сокращенные дни. Параметр: 'short_day'")
    @Test
    public void parameterHolyDayType() {
        JsonPath jsonPath = JsonPath.given(given().queryParam("day_type", "holy_day").log().all()
                .spec(requestSpecification)
                .when()
                .get("/Calendar/GetHolidays")
                .then()
                .spec(responseSpecification)
                .body("response.items.type", everyItem(startsWith("holy_day")))
                .extract().body().jsonPath().prettyPrint());

        List<HolidayItem> items =
                jsonPath.getList("response.items", HolidayItem.class);
        boolean DAY_TYPE = items.stream().allMatch((x) -> Objects.equals(x.getType(), "holy_day"));
        Assert.assertTrue(DAY_TYPE, "AssertionFailedError: There are no days with type 'holy_day'");
    }

    @Description("Тест API. Проверка календаря на год, непристуствующий в календаре. Параметр: '1000'. Негативный тест")
    @Test
    public void parameterMissingYear() {
        JsonPath jsonPath = JsonPath.given(given().queryParam("year", "1000").log().all()
                .when()
                .spec(requestSpecification)
                .when()
                .get("/Calendar/GetHolidays")
                .then()
                .spec(responseSpecification)
                .body("response.items.date", everyItem(startsWith("1000")))
                .extract().body().jsonPath().prettyPrint());
        List<HolidayItem> items =
                jsonPath.getList("response.items", HolidayItem.class);
        boolean DAY_COUNT = items.stream().allMatch((x) -> Objects.equals(x.getCount(), 0));
        Assert.assertFalse(DAY_COUNT, "AssertionFailedError: There are no days in he year 1000'");
    }

    @Description("Тест API. Проверка календаря на сверхурочную работу. Параметр: 'over_time'. Негативный тест. day_type может принимать только два значения.")
    @Test
    public void parameter() {
        JsonPath jsonPath = JsonPath.given(given().queryParam("day_type", "over_time").log().all()
                .spec(requestSpecification)
                .when()
                .get("/Calendar/GetHolidays")
                .then()
                .spec(responseSpecification)
                .body("response.items.type", everyItem(startsWith("over_time")))
                .extract().body().jsonPath().prettyPrint());
        List<HolidayItem> items =
                jsonPath.getList("response.items", HolidayItem.class);
        boolean DAY_COUNT = items.stream().allMatch((x) -> Objects.equals(x.getCount(), 0));
        Assert.assertFalse(DAY_COUNT, "AssertionFailedError: There are no days with type 'over_time'");
    }
}









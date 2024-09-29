
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class AppCardDeliveryTest {


    private String dataGenerate(int step) {
        return LocalDate.now().plusDays(step).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    String validDate = dataGenerate(4);
    String invalideDate = dataGenerate(-1);



    @Test
    public void validTestAppCardDelivery() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Волгоград");
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").setValue(validDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79951233434");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(16));
        $(withText("Встреча забронирована на" + validDate));
    }

    @Test
    public void inValidCityTestAppCardDelivery() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Алания");
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").setValue(validDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79951233434");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Доставка в выбранный город недоступна")).shouldBe(visible);
    }

    @Test
    public void inValidEngTestAppCardDelivery() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Volgograd");
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").setValue(validDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79951233434");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Доставка в выбранный город недоступна")).shouldBe(visible);
    }

    @Test
    public void inValidDateTestAppCardDelivery() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Волгоград");
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").setValue(invalideDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79951233434");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Заказ на выбранную дату невозможен")).shouldBe(visible);
    }

    @Test
    public void inValidNameTestAppCardDelivery() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Волгоград");
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").setValue(validDate);
        $("[data-test-id=name] input").setValue("Ivanov Ivan");
        $("[data-test-id=phone] input").setValue("+79951233434");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible);
    }

    @Test
    public void invalidPhoneTestAppCardDeliver() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Волгоград");
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").setValue(validDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("799512334355");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible);
    }

    @Test
    public void invalidCheckBoxTestAppCardDeliver() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Волгоград");
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").setValue(validDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79951233434");
        $(".button").click();
        $(byText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).shouldBe(visible);
    }

   @Test
  public void datePicker()  {
       open("http://localhost:9999/");
       $("[data-test-id='city'] input").setValue("Во");
       $(".input__menu").find(withText("Волгоград")).click();
       $(".calendar-input__custom-control").click();
       $$(".calendar__day").findBy(text("5")).click();
       $("[data-test-id=name] input").setValue("Иванов Иван");
       $("[data-test-id=phone] input").setValue("+79951233434");
       $("[data-test-id=agreement]").click();
       $(".button").click();
       $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(16));
       $(withText("Встреча забронирована на" + validDate));
       }
   }


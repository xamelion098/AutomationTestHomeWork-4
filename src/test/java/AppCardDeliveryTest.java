import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {

    private String dataGenerate() {
        return LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private String invalidDataGenerate() {
        return LocalDate.now().plusDays(-2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String date = dataGenerate();
    String inValidData = invalidDataGenerate();


    @Test
    public void validTestAppCardDelivery() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Волгоград");
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79951233434");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(16));
        $(withText("Встреча забронирована на" + date));
    }

    @Test
    public void inValidCityTestAppCardDelivery() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Алания");
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").setValue(date);
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
        $("[data-test-id=date] input").setValue(date);
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
        $("[data-test-id=date] input").setValue(inValidData);
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
        $("[data-test-id=date] input").setValue(date);
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
        $("[data-test-id=date] input").setValue(date);
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
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79951233434");
        $(".button").click();
        $(byText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).shouldBe(visible);
    }

    @Test
    public void complexElementTest() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Во");
        $$(".menu-item__control").last().click();
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("body > div.popup.popup_direction_bottom-left.popup_target_anchor.popup_size_s.popup_visible.popup_padded.popup_theme_alfa-on-white > div > div > div > div > div > div > div:nth-child(4)").click();
        $("body > div.popup.popup_direction_bottom-left.popup_target_anchor.popup_size_s.popup_visible.popup_padded.popup_theme_alfa-on-white > div > div > div > div > div > table > tbody > tr:nth-child(2) > td:nth-child(3)").click();
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79951233434");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(16));
        $(withText("Встреча забронирована на" + date));

    }
}
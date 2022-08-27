package lession10Jenkins.demoqa;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class RegistrationFormTest extends TestBase {


    @Owner("DariaSmit")
    @Severity(SeverityLevel.MINOR)
    @Feature("Тестинг Тестинг с PageObject и Faker")
    @Story("Тестинг формы регистрации:чистый Selenide")

    @Test
    @DisplayName("Testing the students registration form")
    void fillFormTest() {
        step("open registration form", () -> {
            Selenide.open("/automation-practice-form");
        });
        Faker faker = new Faker();
        String firstName = faker.name().firstName(),
                lastName = faker.name().firstName(),
                userEmail = faker.internet().emailAddress(),
                phone = "1234567891",
                gender = "Male",
                address = "Moscow street 15",
                picture = "OMG.png";

        step("Fill registration form", () -> {


            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(userEmail);
            $("#userNumber").setValue(phone);
            $(byText(gender)).click();

            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("March");
            $(".react-datepicker__year-select").selectOption("1990");
            $("[aria-label$='March 13th, 1990']").click();

            $("#subjectsInput").setValue("Physics").pressEnter();
            $(byText("Sports")).click();
            $("#uploadPicture").uploadFromClasspath(picture);
            $("#currentAddress").setValue(address);
            $("#stateCity-wrapper").click();
            $(byText("NCR")).click();
            $("#city").click();
            $(byText("Noida")).click();
            $("#submit").click();
        });

        step("Verify form data", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(
                    text(firstName),
                    text(lastName),
                    text(userEmail),
                    text(phone),
                    text(address));
        });
    }

}
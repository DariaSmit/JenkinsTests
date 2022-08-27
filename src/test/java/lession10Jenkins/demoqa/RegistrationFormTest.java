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

        Faker faker = new Faker();
        String firstName = faker.name().firstName(),
                lastName = faker.name().firstName(),
                userEmail = faker.internet().emailAddress(),
                phone = "1234567891",
                gender = "Male",
                address = "Moscow street 15",
                picture = "OMG.png";

        step("открыть форму регистрации", () -> {
            Selenide.open("/automation-practice-form");
        });

        step("Ввести имя", () -> {
            $("#firstName").setValue(firstName);});
        step("ввести фамилию",()->{
            $("#lastName").setValue(lastName);});
        step("ввести эмеил'",()->{
            $("#userEmail").setValue(userEmail);});
        step("ввести номер телефона",()->{
            $("#userNumber").setValue(phone);});
        step("указать пол", ()->{
            $(byText(gender)).click();});
        step("указать день рождения", ()->{
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("March");
            $(".react-datepicker__year-select").selectOption("1990");
            $("[aria-label$='March 13th, 1990']").click();});
        step("выбрать навыки", ()->{
            $("#subjectsInput").setValue("Physics").pressEnter();});
            $(byText("Sports")).click();
        step("загрузить изображение", ()-> {
            $("#uploadPicture").uploadFromClasspath(picture);});
        step("ввести адрес", ()->{
            $("#currentAddress").setValue(address);
            $("#stateCity-wrapper").click();
            $(byText("NCR")).click();
            $("#city").click();
            $(byText("Delhi")).click();});
         step("подтвердить заполнение анкеты", ()->{
            $("#submit").click();});

        step("Проверка полей", () ->{
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
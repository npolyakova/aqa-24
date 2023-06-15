package bank.xyz;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest extends WebTest {

    final HomePage homePage = new HomePage();

    final LoginPage loginPage = new LoginPage();

    final AccountPage accountPage = new AccountPage();

    @Step(value = "Авторизоваться")
    public void login() {
            open("/login");
            homePage.buttonCustomerLogin.click();

            loginPage.inputUsername.shouldBe(visible);
            loginPage.inputUsername.selectOption(4);

            loginPage.buttonLogin.click();
            accountPage.labelWelcomePhrase.shouldBe(visible);//.shouldHave(text("Welcome Neville Longbottom !!"));
    }
}

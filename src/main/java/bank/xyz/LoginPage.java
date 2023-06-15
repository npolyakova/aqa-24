package bank.xyz;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    SelenideElement inputUsername = $("#userSelect");

    SelenideElement buttonLogin = $(byText("Login"));


}

package bank.xyz;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    SelenideElement form = $("[name='myForm']");
}

package bank.xyz;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AccountPage extends BasePage {

    SelenideElement labelWelcomePhrase = $(".borderM strong");

    ElementsCollection blockAccountInfo = $$("[ng-hide='noAccount'] strong");

    SelenideElement buttonDeposit = $("[ng-class='btnClass2']");

    SelenideElement buttonWithdraw = $("[ng-class='btnClass3']");

    SelenideElement labelAmountToBeDeposited = $(byText("Amount to be Deposited :"));

    SelenideElement labelAmountToBeWithdrawn = $(byText("Amount to be Withdrawn :"));

    SelenideElement inputAmount = $("[ng-model='amount']");

    SelenideElement buttonCreateDeposit = form.$(byText("Deposit"));

    SelenideElement buttonCreateWithdraw = form.$(byText("Withdraw"));

    public void sdf() {
    }
}

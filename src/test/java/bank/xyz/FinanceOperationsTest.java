package bank.xyz;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class FinanceOperationsTest extends BaseTest {

    private Integer amountToDeposit;

    private Integer amountToWithdraw;

    @BeforeEach
    public void setUp() {
        Configuration.baseUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#";
    }

    @Test
    @DisplayName("Пополнение счета")
    public void shouldCreateDeposit() {
        login();
        Integer balance = Integer.valueOf(accountPage.blockAccountInfo.get(1).as("Balance").getText());
        createDeposit();
        checkBalance(balance);
    }

    @Test
    @DisplayName("Вывод средств")
    public void shouldCreateWithdraw() {
        login();
        createDeposit();

        step("Перейти на вкладку 'Withdraw'", () -> {
            accountPage.buttonCreateWithdraw.click();
            accountPage.labelAmountToBeWithdrawn.shouldBe(visible);
        });

        step("Указать сумму для вывода со счета и нажать кнопку 'Withdraw'", () -> {
            amountToWithdraw = new Random().nextInt(amountToDeposit);
            $("[ng-model='amount']").sendKeys(amountToWithdraw.toString());
            $("[name='myForm']").$(byText("Withdraw")).click();
        });

        step("Проверить, что деньги выведены", () -> {
            $$("[ng-hide='noAccount'] strong").get(1).shouldHave(
                    text(String.valueOf(amountToDeposit - amountToWithdraw))
            );
        });
    }

//    @Test
//    void shouldCheckStyles() {
//        Condition correctColor = Condition.cssValue("color", "#6c0b0b");
//        accountPage.buttonCreateWithdraw.shouldHave(correctColor);
//        accountPage.buttonCreateWithdraw.shouldHave(cssClass("btn-lg"));
//        accountPage.buttonCreateWithdraw.shouldHave(cssClass("tab"));
//        accountPage.buttonCreateWithdraw.shouldHave(cssClass("btn-primary"));
//    }

    @Test
    void accountPageShouldLookGood() throws IOException {
        Configuration.timeout = 7000;
        Configuration.browserSize = "1200x1000";
        login();
        Screenshot screenshot = new AShot()
                .takeScreenshot(WebDriverRunner.getWebDriver(), accountPage.buttonDeposit);

        ImageIO.write(screenshot.getImage().getSubimage(20, 90, 50, 40), "PNG", new File("src/test/resources/accountButtonDeposit.png"));

//        BufferedImage idealImage = ImageIO.read(new File("src/test/resources/account.png"));
//
//        ImageDiffer differ = new ImageDiffer();
//        ImageDiff result = differ.makeDiff(screenshot.getImage(), idealImage);
//
//        ImageIO.write(result.getMarkedImage(), "PNG", new File("src/test/resources/accountDiff.png"));
//        assertThat(result.hasDiff()).isFalse();
    }

    private void createDeposit() {
        step("Перейти на вкладку 'Deposit' ", () -> {
            accountPage.buttonDeposit.click();
            accountPage.labelAmountToBeDeposited.shouldBe(visible);
        });

        step("Указать сумму для пополнения счета и нажать кнопку 'Deposit'", () -> {
            amountToDeposit = new Random().nextInt(1000);

            accountPage.inputAmount.sendKeys(String.valueOf(amountToDeposit));
            accountPage.buttonCreateDeposit.click();

            return amountToDeposit;
        });
    }

    private void checkBalance(Integer balance) {
        step("Проверить, что счет пополнен", () -> {
            $("[ng-show='message']").shouldHave(text("Deposit Successful"));
            final Integer newBalance = Integer.valueOf($$("[ng-hide='noAccount'] strong").get(1).getText());
            assertThat(newBalance - balance).isEqualTo(amountToDeposit);
        });
    }
}

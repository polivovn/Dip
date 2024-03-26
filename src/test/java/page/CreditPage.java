package page;


import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
        import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import data.DataHelper;

public class CreditPage {
    private static DataHelper.CardInfo cardInfo;
    private SelenideElement heading = $$("h3").find(text("Кредит по данным карты"));
    private static SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private static SelenideElement monthField = $("[placeholder='08']");
    private static SelenideElement yearField = $("[placeholder='22']");
    private static SelenideElement holderField = $(byText("Владелец")).parent().$(".input__control");
    private static SelenideElement cvcField = $("[placeholder='999']");
    private static SelenideElement buttonContinue = $$("button").find(exactText("Продолжить"));


    private static SelenideElement successNOTIF = $(".notification_status_ok");
    private static SelenideElement errorNOTIF = $(".notification_status_error");
    private static SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));
    private static SelenideElement invalidFormat = $(byText("Неверный формат"));
    private static SelenideElement invalidCharMessage = $(byText("Поле содержит недопустимые символы"));
    private static SelenideElement invalidCardExpirationDateMessage = $(byText("Неверно указан срок действия карты"));
    private static SelenideElement cardExpiredMessage = $(byText("Истёк срок действия карты"));

    public CreditPage() {
        heading.shouldBe(visible);
    }

    public static void fillInCardInfo(DataHelper.CardInfo cardInfo) {
        CreditPage.cardInfo = cardInfo;
        cardNumberField.sendKeys(cardInfo.getCardNumber());
        monthField.sendKeys(cardInfo.getMonth());
        yearField.sendKeys(cardInfo.getYear());
        holderField.sendKeys(cardInfo.getHolder());
        cvcField.sendKeys(cardInfo.getCvc());
        buttonContinue.click();
    }



    public static void textValidationForTheCVCField(String text) {
        cvcField.shouldHave(text(text), Duration.ofSeconds(10)).shouldBe(visible);
    }


    public static void setSuccessNotificationVisible() {
        successNOTIF.shouldBe(visible, Duration.ofSeconds(10));
    }

    public static void setErrorNotificationVisible() {
        errorNOTIF.shouldBe(visible, Duration.ofSeconds(10));
    }

    public static void setRequiredFieldVisible() {
        requiredField.shouldBe(visible, Duration.ofSeconds(10));
    }

    public static void setInvalidFormatVisible() {
        invalidFormat.shouldBe(visible, Duration.ofSeconds(10));
    }

    public static void setInvalidCharMessageVisible() {
        invalidCharMessage.shouldBe(visible, Duration.ofSeconds(10));
    }

    public static void setInvalidCardExpirationDateMessageVisible() {
        invalidCardExpirationDateMessage.shouldBe(visible, Duration.ofSeconds(10));
    }

    public static void setCardExpiredMessageVisible() {
        cardExpiredMessage.shouldBe(visible, Duration.ofSeconds(10));
    }
}

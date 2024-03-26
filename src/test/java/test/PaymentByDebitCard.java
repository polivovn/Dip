package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.CreditPage;
import page.MainPage;
import page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PaymentByDebitCard {
    private MainPage mainPage;
    private CreditPage creditPage;
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setupTest() {

        open("http://localhost:8080/");
        mainPage = new MainPage();
        creditPage = mainPage.goToCreditPage();
    }
    @AfterEach
    public void cleanBase() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("")
    void shouldTestBuyCardForStatusApproved() {

        CreditPage.fillInCardInfo(DataHelper.getCardApproved());
        CreditPage.setSuccessNotificationVisible();
        assertEquals("APPROVED", SQLHelper.getStatusForCredit());
    }
    @Test
    @DisplayName("")
    void shouldTestBuyCardForStatusDeclined() {

        CreditPage.fillInCardInfo(DataHelper.getCardDeclined());
        CreditPage.setErrorNotificationVisible();
        assertEquals("DECLINED", SQLHelper.getStatusForCredit());
    }
    @Test
    @DisplayName("Ввести 0000 0000 0000 0000.")
    void shouldTestTheBuyWithACardNumberZero() {

        CreditPage.fillInCardInfo(DataHelper.getCardNumberZero());
        CreditPage.setErrorNotificationVisible();
    }
    @Test
    @DisplayName("Номер карты» оставить пустым")
    void shouldTestThePurchaseWithAnEmptyCardNumberField() {

        CreditPage.fillInCardInfo(DataHelper.getNonCardNumber());
        CreditPage.setRequiredFieldVisible();
    }
    @Test
    @DisplayName("Ввести номер месяца больше 12")
    void shouldTestMonthFieldForOverUpperBoundaryValue() {

        CreditPage.fillInCardInfo(DataHelper.getLeaveTheMonthFieldBlank());
        CreditPage.setInvalidCardExpirationDateMessageVisible();
    }
    @Test
    @DisplayName("Истёкший срок действия карты.")
    void shouldTestPatsValueForYearField() {

        CreditPage.fillInCardInfo(DataHelper.getCardHasExpired());
        CreditPage.setCardExpiredMessageVisible();
    }
    @Test
    @DisplayName("Поле «Месяц» оставить пустым")
    void shouldTestEmptyMonthField() {

        CreditPage.fillInCardInfo(DataHelper.getMonthEmptyField());
        CreditPage.setRequiredFieldVisible();
    }
    @Test
    @DisplayName("Поле ввода «Год» оставить пустым")
    void shouldTestEmptyYearField() {

        CreditPage.fillInCardInfo(DataHelper.getYearEmptyField());
        CreditPage.setRequiredFieldVisible();
    }
    @Test
    @DisplayName("В поле ввода «Владелец» цифры")
    void shouldTestHolderForDigits() {

        CreditPage.fillInCardInfo(DataHelper.getInTheOwnerInputFieldNumbers());
        CreditPage.setInvalidCharMessageVisible();
    }
    @Test
    @DisplayName("В поле ввода «Владелец» спецсимволы")
    void shouldTestHolderForSpecialCharacters() {

        CreditPage.fillInCardInfo(DataHelper.getSpecialCharactersInTheOwnerInputField());
        CreditPage.setInvalidCharMessageVisible();
    }
    @Test
    @DisplayName("Поле «CVC/CVV» пустое")
    void shouldTestEmptyCVCField() {

        CreditPage.fillInCardInfo(DataHelper.getCVCEmptyField());
        CreditPage.textValidationForTheCVCField("Поле обязательно для заполнения");

    }
    @Test
    @DisplayName("В поле «CVC/CVV» ввести 000.")
    void shouldTestCVCFieldOfZero() {

        CreditPage.fillInCardInfo(DataHelper.getCVCOfZero());
        CreditPage.setInvalidFormatVisible();
    }
}







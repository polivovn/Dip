package data;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private static final Faker fakerRu = new Faker(new Locale("ru"));
    private static final Faker fakerEn = new Faker(new Locale("en"));
    private static Faker fakerLangEn;
    private static Condition fakerLangRu;

    public static CardInfo getCardApproved() {
        return new CardInfo("4444 4444 4444 4441",
                "10", "27", "Vovnova Polina", "456");
    }

    public static CardInfo getCardDeclined() {
        return new CardInfo("4444 4444 4444 4442",
                "10", "27", "Vovnova Polina", "456");
    }

    public static CardInfo getCardNumberZero() {   ///только ноль
        return new CardInfo("0000 0000 0000 0000",
                "10", "27", "Vovnova Polina", "456");
    }


    public static CardInfo getNonCardNumber() { /// пустой номер карты
        return new CardInfo("",
                "10", "27", "Vovnova Polina", "456");
    }

    public static CardInfo getLeaveTheMonthFieldBlank() {  // месяц 19 не существует
        return new CardInfo("4444 4444 4444 4441",
                "19", "27", "Vovnova Polina", "456");
    }

    public static CardInfo getMonthEmptyField() {  // месяц пустой
        return new CardInfo("4444 4444 4444 4441",
                "", "27", "Vovnova Polina", "456");
    }

    public static CardInfo getCardHasExpired() {  // истекший срок действия карты
        return new CardInfo("4444 4444 4444 4441",
                "10", "22", "Vovnova Polina", "456");
    }

    public static CardInfo getYearEmptyField() {  // год пустой
        return new CardInfo("4444 4444 4444 4441",
                "10", "", "Vovnova Polina", "456");
    }

    public static CardInfo getInTheOwnerInputFieldNumbers() {  // В поле  ввести цифры.
        return new CardInfo("4444 4444 4444 4441",
                "10", "27", "Vovn675ova Polina", "456");
    }

    public static CardInfo getSpecialCharactersInTheOwnerInputField() {  // ввести спецсимволы.
        return new CardInfo("4444 4444 4444 4441",
                "10", "27", "Vovn@ova Polina", "456");
    }

    public static CardInfo getCVCEmptyField() {  // Поле «CVC/CVV» оставить пустым.
        return new CardInfo("4444 4444 4444 4441",
                "10", "27", "Vovnova Polina", "");
    }

    public static CardInfo getCVCOfZero() {  // В поле «CVC/CVV» ввести 000.
        return new CardInfo("4444 4444 4444 4441",
                "10", "27", "Vovnova Polina", "000");
    }

    // ПУСТЫЕ ПОЛЯ ВВОДА
    public static CardInfo getFormFromEmptyFields() {
        return new CardInfo("", "", "", "", "");
    }

    public static String getValidMonth() {
        String validMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        return validMonth;
    }

    public static String getPastYear() {
        String pastYear = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return pastYear;
    }

    public static String getCurrentYear() {
        String currentYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        return currentYear;
    }

    public static String getAlwaysValidYear() {
        String alwaysValidYear = LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
        return alwaysValidYear;
    }

    public static String getFutureYear() {
        String futureYear = LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
        return futureYear;
    }


    public static String getValidHolder() {
        return fakerLangEn.name().firstName().toUpperCase() + " " + fakerLangEn.name().lastName().toUpperCase();
    }



    public static String generateRussianName() {
        String[] names = {"Иван", "Александр", "Екатерина", "Мария", "Дмитрий", "Анна"};
        Random random = new Random();
        int index = random.nextInt(names.length);
        return names[index];
    }
    public static String getRandomCardNumber() {
        return fakerLangEn.business().creditCardNumber();
    }

    public static String getValidCVV() {
        return fakerLangEn.number().digits(3);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String holder;
        private String cvc;
    }
}
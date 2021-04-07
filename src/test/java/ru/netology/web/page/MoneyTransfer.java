package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransfer {
    private SelenideElement transferPage = $(withText("Пополнение карты"));
    private SelenideElement amount = $("[data-test-id = 'amount']");
    private SelenideElement transferFrom = $("[data-test-id = 'from'] input");
    private SelenideElement transferTo = $("[data-test-id = 'to'] input");
    private SelenideElement buttonTopUp = $(withText("Пополнить"));

    public void moneyTransferVisible() {
        transferPage.shouldBe(Condition.visible);
    }

    public void setTransferFrom(String numberCard) {
        transferFrom.setValue(numberCard);
    }

    public void setTransferTo(String numberCard) {
        transferFrom.setValue(numberCard);
    }

    public void makeTransfer() {
        buttonTopUp.click();
    }
}

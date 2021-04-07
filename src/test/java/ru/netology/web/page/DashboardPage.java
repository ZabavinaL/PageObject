package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id = 'dashboard']");

    private SelenideElement buttonCard1 = $$("[data-test-id = 'action-deposit']").first();
    private SelenideElement buttonCard2 = $$("[data-test-id = 'action-deposit']").last();
    private SelenideElement balanceCard1 = $("[data-test-id = '92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement balanceCard2 = $("[data-test-id = '0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    private ElementsCollection cards = $$(".list__item");
    private final String balanceStartCard1 = "баланс: ";
    private final String balanceFinishCard1 = " р.";
    private final String balanceStartCard2 = "баланс: ";
    private final String balanceFinishCard2 = " р.";

    public DashboardPage() {
    }

    public void dashboardPageVisible() {
        heading.shouldBe(Condition.visible);
    }

    public int getBalanceCard1() {
        val text = cards.first().text();
        return extractBalanceCard1(text);
    }

    public int getBalanceCard2() {
        val text = cards.last().text();
        return extractBalanceCard2(text);
    }

    private int extractBalanceCard1(String text) {
        val start = text.indexOf(balanceStartCard1);
        val finish = text.indexOf(balanceFinishCard1);
        val value = text.substring(start + balanceStartCard1.length(), finish);
        return Integer.parseInt(value);
    }

    private int extractBalanceCard2(String text) {
        val start = text.indexOf(balanceStartCard2);
        val finish = text.indexOf(balanceFinishCard2);
        val value = text.substring(start + balanceStartCard2.length(), finish);
        return Integer.parseInt(value);
    }


    public MoneyTransfer topUpTheCard1() {
        buttonCard1.click();
        return new MoneyTransfer();
    }

    public MoneyTransfer topUpTheCard2() {
        buttonCard2.click();
        return new MoneyTransfer();
    }
}
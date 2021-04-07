package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    private DashboardPage openPage() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        return verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldMoneyTransferFromSecondToFirstCard() { //пополнение первой карты
        DashboardPage dashboardPage = openPage();//вход в лк
        dashboardPage.dashboardPageVisible();

        //пополнение
        val moneyTransfer = dashboardPage.topUpTheCard1();
        moneyTransfer.moneyTransferVisible();
        int amountTransfer = 20;
        moneyTransfer.setTransferFrom(DataHelper.getCardNumber2());
        moneyTransfer.setTransferTo(DataHelper.getCardNumber1());
        moneyTransfer.makeTransfer();

        val balanceCard1BeforeTransfer = dashboardPage.getBalanceCard1();
        val balanceCard2BeforeTransfer = dashboardPage.getBalanceCard2();

        int newBalanceCard2 = balanceCard2BeforeTransfer - amountTransfer;
        int newBalanceCard1 = balanceCard1BeforeTransfer + amountTransfer;
        assertEquals(newBalanceCard1, balanceCard1BeforeTransfer);
        assertEquals(newBalanceCard2, balanceCard2BeforeTransfer);
    }

    @Test
    void shouldMoneyTransferFromFirstToSecondCard() { //пополнение второй карты
        DashboardPage dashboardPage = openPage();//вход в лк
        dashboardPage.dashboardPageVisible();

        //пополнение
        val moneyTransfer = dashboardPage.topUpTheCard2();
        moneyTransfer.moneyTransferVisible();
        int amountTransfer = 1000;
        moneyTransfer.setTransferFrom(DataHelper.getCardNumber1());
        moneyTransfer.setTransferTo(DataHelper.getCardNumber2());
        moneyTransfer.makeTransfer();

        val balanceCard1BeforeTransfer = dashboardPage.getBalanceCard1();
        val balanceCard2BeforeTransfer = dashboardPage.getBalanceCard2();

        int newBalanceCard2 = balanceCard2BeforeTransfer - amountTransfer;
        int newBalanceCard1 = balanceCard1BeforeTransfer + amountTransfer;
        assertEquals(newBalanceCard1, balanceCard1BeforeTransfer);
        assertEquals(newBalanceCard2, balanceCard1BeforeTransfer);
    }
}

package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999", LoginPage.class);
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationInfo);
    }

    @Test
    void shouldTransferFromFirstToSecond() {
        var dashboardPage = new DashboardPage();
        int firstBalance = dashboardPage.getFirstCardBalance();
        int secondBalance = dashboardPage.getSecondCardBalance();
        var moneyTransfer = dashboardPage.secondCardButton();
        String total = "5000";
        var card = DataHelper.getFirstCard();
        moneyTransfer.shouldTransfer(total, card);

        assertEquals(secondBalance + Integer.parseInt(total), dashboardPage.getSecondCardBalance());
        assertEquals(firstBalance - Integer.parseInt(total), dashboardPage.getFirstCardBalance());

    }

    @Test
    void shouldTransferFromSecondToFirst() {
        var dashboardPage = new DashboardPage();
        int firstBalance = dashboardPage.getFirstCardBalance();
        int secondBalance = dashboardPage.getSecondCardBalance();
        var moneyTransfer = dashboardPage.firstCardButton();
        String total = "5000";
        var card = DataHelper.getSecondCard();
        moneyTransfer.shouldTransfer(total, card);

        assertEquals(firstBalance + Integer.parseInt(total), dashboardPage.getFirstCardBalance());
        assertEquals(secondBalance - Integer.parseInt(total), dashboardPage.getSecondCardBalance());

    }

    @Test
    void shouldNotTransferAboveBalance() {
        var dashboardPage = new DashboardPage();
        var moneyTransfer = dashboardPage.secondCardButton();
        String total = "15000";
        var card = DataHelper.getFirstCard();
        moneyTransfer.shouldTransfer(total, card);
        moneyTransfer.errorNotification();
    }
}
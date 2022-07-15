package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.Integer.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardPage {
    private ElementsCollection cards = $$x("//li[@class='list__item']/div");
    private ElementsCollection actionButton = $$x("//button[@data-test-id='action-deposit']");
    private SelenideElement reloadButton = $x("//button[@data-test-id='action-reload']");
    private SelenideElement errorNotification = $x("//div[@data-test-id='error-notification']");

    public TransferPage transferClick(int indexCardTo) {
        actionButton.get(indexCardTo).click();
        return new TransferPage();
    }

    public int getBalance(int index) {
        reloadButton.click();
        String[] card = cards.get(index).toString().split(" ");
        int balance = valueOf(card[6]);
        return balance;
    }

    public void assertBalance(int index, int expectedBalance) {
        int actualBalance = getBalance(index);
        assertEquals(expectedBalance, actualBalance);
    }
}

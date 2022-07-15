package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.UserData;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeInput = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");

    public DashboardPage verify(UserData user) {
        codeInput.setValue(user.getVerifyCode());
        verifyButton.click();
        return new DashboardPage();
    }
}

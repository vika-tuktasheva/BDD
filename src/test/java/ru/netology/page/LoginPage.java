package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.UserData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement loginInput = $("[data-test-id='login'] input");
    private SelenideElement passwordInput = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $x("//button[@data-test-id='action-login']");

    public VerificationPage login(UserData user) {
        loginInput.setValue(user.getLogin());
        passwordInput.setValue(user.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}

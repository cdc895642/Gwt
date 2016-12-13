package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.http.client.URL;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by cdc89 on 10.12.2016.
 */
public class LoginPage extends Composite {
    interface LoginPageUiBinder extends UiBinder<HorizontalPanel, LoginPage> {
    }

    private static Logger logger = Logger.getLogger("LoginPage");
    private static LoginPageUiBinder ourUiBinder = GWT.create(LoginPageUiBinder.class);
    @UiField
    TextBox login;
    @UiField
    PasswordTextBox password;
    @UiField
    Button signin;
    @UiField
    InlineLabel loginLabel;
    @UiField
    InlineLabel passwordLabel;

public static native String alert() /*-{
    var a= new Date();
    return a.getTime();
}-*/;

    public LoginPage() {
        super();
        initWidget(ourUiBinder.createAndBindUi(this));

        loginLabel.getElement().setInnerText("Пользователь :");//constants.userLabelConstant()
        passwordLabel.getElement().setInnerText("Пароль :");
        login.getElement().setAttribute("placeHolder", "логин");
        password.getElement().setAttribute("placeHolder", "пароль");
        signin.addClickHandler(event -> {
            String loginText = login.getText();//getElement().getInnerText();
            String passwordText = password.getValue();
            String locale=LocaleInfo.getCurrentLocale().getLocaleName();
            String time=alert();
            time=URL.decode(time);
            locale=URL.decode(locale);
            logger.info("LoginPage - button click");
            logger.info("LoginPage - login : "+loginText);
            logger.info("LoginPage - password : "+passwordText);
            MainPage mainPage = new MainPage();
            MyAsyncCallback myAsyncCallback = new MyAsyncCallback(mainPage);
            MySampleApplicationService.App.getInstance().getMessage(loginText, passwordText, time, locale, myAsyncCallback);
            RootPanel.get("main").clear();
            RootPanel.get("main").add(mainPage);
        });
    }

    private static class MyAsyncCallback implements AsyncCallback<MySampleApplicationService.Result> {
        private MainPage mainPage;

        public MyAsyncCallback(MainPage mainPage) {
            this.mainPage = mainPage;
        }

        public void onSuccess(MySampleApplicationService.Result result) {
            mainPage.setHelloLabelText(result.answerText);
            mainPage.setLogoutLinkText(result.linkText);
        }

        public void onFailure(Throwable throwable) {
            mainPage.setHelloLabelText("enter correct login and password");
            mainPage.setLogoutLinkText("back");
        }
    }
}
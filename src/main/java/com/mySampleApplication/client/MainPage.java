package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import java.util.logging.Logger;

/**
 * Created by cdc89 on 10.12.2016.
 */
public class MainPage extends Composite {
    interface MainPageUiBinder extends UiBinder<VerticalPanel, MainPage> {
    }

    @UiField
    Label helloLabel;
    @UiField
    Anchor logoutLink;

    private static MainPageUiBinder ourUiBinder = GWT.create(MainPageUiBinder.class);
    private static Logger logger = Logger.getLogger("MainPage");

    public MainPage() {
        super();
        initWidget(ourUiBinder.createAndBindUi(this));
        //helloLabel.getElement().setInnerText("Hello User");
        //logoutLink.getElement().setInnerText("logout user");
        logoutLink.setHref("#blank");

        logoutLink.addClickHandler(event->{
            logger.info("MainPage - back link clicked");
            RootPanel.get("main").clear();
            RootPanel.get("main").add(new LoginPage());
        });
    }

    public void setHelloLabelText(String text){
        helloLabel.getElement().setInnerText(text);
    }

    public void setLogoutLinkText(String text){
        logoutLink.getElement().setInnerText(text);
    }
}
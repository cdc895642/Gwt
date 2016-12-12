package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.mySampleApplication.hibernate.service.UserService;
import com.mySampleApplication.hibernate.util.HibernateUtil;
import com.mySampleApplication.server.MyRemoteLoggingServlet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.logging.Logger;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

    private static Logger logger = Logger.getLogger(MySampleApplication.class.getName());
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
logger.info("page loaded");
        RootPanel.get("main").add(new LoginPage());

        final Button button = new Button("Click me");
        final Label label = new Label();
        RootPanel.get("main").add(label);
 //       MySampleApplicationService.App.getInstance().getMessage("Hello, World!","", new MyAsyncCallback(label));

        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (label.getText().equals("")) {
       //             MySampleApplicationService.App.getInstance().getMessage("Hello, World!","", new MyAsyncCallback(label));
                } else {
                    label.setText("");
                }
            }
        });

        // Assume that the host HTML has elements defined whose
        // IDs are "slot1", "slot2".  In a real app, you probably would not want
        // to hard-code IDs.  Instead, you could, for example, search for all
        // elements with a particular CSS class and replace them with widgets.
        //
//        RootPanel.get("slot1").add(button);
//        RootPanel.get("slot2").add(label);
    }

    private static class MyAsyncCallback implements AsyncCallback<MySampleApplicationService.Result> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(MySampleApplicationService.Result result) {
            label.getElement().setInnerHTML(result.answerText);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}

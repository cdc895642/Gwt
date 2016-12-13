package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

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
    }
}

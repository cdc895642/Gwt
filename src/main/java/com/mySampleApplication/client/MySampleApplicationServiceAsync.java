package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MySampleApplicationServiceAsync {
    void getMessage(String login, String password, String time, String locale, AsyncCallback<MySampleApplicationService.Result> async);
}

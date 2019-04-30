package com.example.demogiuakyandroidnc;

import org.json.JSONArray;

public interface ILoginView {
    void onLoginSuccess(String m);
    void onLoginFail(String m);
    void onLoginUserId(String id);
}
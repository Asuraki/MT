package com.synebo.beans;

import com.univocity.parsers.annotations.*;

public class SettingsBean {
    @Parsed(field = "Login")
    private String Login;

    @Parsed(field = "Password")
    private String Password;

    @Parsed(field = "Token")
    private String Token;

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return Password;
    }

    public String getToken() {
        return Token;
    }
}

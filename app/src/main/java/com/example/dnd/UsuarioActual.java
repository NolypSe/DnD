package com.example.dnd;

import android.app.Application;

public class UsuarioActual extends Application {

    private static UsuarioActual instance;
    private boolean isLoggedIn = false;
    private int usuarioActualId;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static UsuarioActual getInstance() {
        return instance;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public int getUsuarioActualId() {
        return usuarioActualId;
    }

    public void setUsuarioActualId(int usuarioActualId) {
        this.usuarioActualId = usuarioActualId;
    }
}

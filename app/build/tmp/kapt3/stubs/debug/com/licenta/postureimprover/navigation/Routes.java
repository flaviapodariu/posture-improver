package com.licenta.postureimprover.navigation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u000b\f\r\u000e\u000fB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001f\u0010\u0007\u001a\u00020\u00032\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\t\"\u00020\u0003\u00a2\u0006\u0002\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0005\u0010\u0011\u0012\u0013\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/licenta/postureimprover/navigation/Routes;", "", "route", "", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "passArgs", "args", "", "([Ljava/lang/String;)Ljava/lang/String;", "Camera", "Dashboard", "Login", "Settings", "SignUp", "Lcom/licenta/postureimprover/navigation/Routes$Login;", "Lcom/licenta/postureimprover/navigation/Routes$Dashboard;", "Lcom/licenta/postureimprover/navigation/Routes$SignUp;", "Lcom/licenta/postureimprover/navigation/Routes$Camera;", "Lcom/licenta/postureimprover/navigation/Routes$Settings;", "app_debug"})
public abstract class Routes {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    
    private Routes(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String passArgs(@org.jetbrains.annotations.NotNull()
    java.lang.String... args) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/licenta/postureimprover/navigation/Routes$Login;", "Lcom/licenta/postureimprover/navigation/Routes;", "()V", "app_debug"})
    public static final class Login extends com.licenta.postureimprover.navigation.Routes {
        @org.jetbrains.annotations.NotNull()
        public static final com.licenta.postureimprover.navigation.Routes.Login INSTANCE = null;
        
        private Login() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/licenta/postureimprover/navigation/Routes$Dashboard;", "Lcom/licenta/postureimprover/navigation/Routes;", "()V", "app_debug"})
    public static final class Dashboard extends com.licenta.postureimprover.navigation.Routes {
        @org.jetbrains.annotations.NotNull()
        public static final com.licenta.postureimprover.navigation.Routes.Dashboard INSTANCE = null;
        
        private Dashboard() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/licenta/postureimprover/navigation/Routes$SignUp;", "Lcom/licenta/postureimprover/navigation/Routes;", "()V", "app_debug"})
    public static final class SignUp extends com.licenta.postureimprover.navigation.Routes {
        @org.jetbrains.annotations.NotNull()
        public static final com.licenta.postureimprover.navigation.Routes.SignUp INSTANCE = null;
        
        private SignUp() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/licenta/postureimprover/navigation/Routes$Camera;", "Lcom/licenta/postureimprover/navigation/Routes;", "()V", "app_debug"})
    public static final class Camera extends com.licenta.postureimprover.navigation.Routes {
        @org.jetbrains.annotations.NotNull()
        public static final com.licenta.postureimprover.navigation.Routes.Camera INSTANCE = null;
        
        private Camera() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/licenta/postureimprover/navigation/Routes$Settings;", "Lcom/licenta/postureimprover/navigation/Routes;", "()V", "app_debug"})
    public static final class Settings extends com.licenta.postureimprover.navigation.Routes {
        @org.jetbrains.annotations.NotNull()
        public static final com.licenta.postureimprover.navigation.Routes.Settings INSTANCE = null;
        
        private Settings() {
            super(null);
        }
    }
}
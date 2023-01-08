package com.licenta.postureimprover.screens.viewmodels;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0006J\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0006J\u000e\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u0006J\u0016\u0010 \u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\"R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR+\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\r\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000b\u00a8\u0006#"}, d2 = {"Lcom/licenta/postureimprover/screens/viewmodels/AuthenticationViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "<set-?>", "", "confirmPassword", "getConfirmPassword", "()Ljava/lang/String;", "setConfirmPassword", "(Ljava/lang/String;)V", "confirmPassword$delegate", "Landroidx/compose/runtime/MutableState;", "email", "getEmail", "setEmail", "email$delegate", "password", "getPassword", "setPassword", "password$delegate", "login", "", "navController", "Landroidx/navigation/NavHostController;", "onConfirmPasswordChanged", "confirmPassState", "onEmailChanged", "emailState", "onPasswordChanged", "passwordState", "register", "context", "Landroid/content/Context;", "app_debug"})
public final class AuthenticationViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState email$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState password$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState confirmPassword$delegate = null;
    private com.google.firebase.auth.FirebaseAuth auth;
    
    @javax.inject.Inject()
    public AuthenticationViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEmail() {
        return null;
    }
    
    public final void setEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPassword() {
        return null;
    }
    
    public final void setPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getConfirmPassword() {
        return null;
    }
    
    public final void setConfirmPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final void onEmailChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String emailState) {
    }
    
    public final void onPasswordChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String passwordState) {
    }
    
    public final void onConfirmPasswordChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String confirmPassState) {
    }
    
    public final void login(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController) {
    }
    
    public final void register(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}
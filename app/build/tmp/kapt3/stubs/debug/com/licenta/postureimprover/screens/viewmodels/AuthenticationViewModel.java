package com.licenta.postureimprover.screens.viewmodels;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0004J\u000e\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0004J\u000e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0004R+\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR+\u0010\f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\r\u0010\u0007\"\u0004\b\u000e\u0010\tR+\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\t\u00a8\u0006\u001b"}, d2 = {"Lcom/licenta/postureimprover/screens/viewmodels/AuthenticationViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "<set-?>", "", "confirmPassword", "getConfirmPassword", "()Ljava/lang/String;", "setConfirmPassword", "(Ljava/lang/String;)V", "confirmPassword$delegate", "Landroidx/compose/runtime/MutableState;", "email", "getEmail", "setEmail", "email$delegate", "password", "getPassword", "setPassword", "password$delegate", "onConfirmPasswordChanged", "", "confirmPassState", "onEmailChanged", "emailState", "onPasswordChanged", "passwordState", "app_debug"})
public final class AuthenticationViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState email$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState password$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState confirmPassword$delegate = null;
    
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
}
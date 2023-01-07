package com.licenta.postureimprover.screens.viewmodels;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0003J\u0006\u0010$\u001a\u00020%R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \u00a8\u0006&"}, d2 = {"Lcom/licenta/postureimprover/screens/viewmodels/CameraViewModel;", "Landroidx/lifecycle/ViewModel;", "selector", "Landroidx/camera/core/CameraSelector;", "provider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "preview", "Landroidx/camera/core/Preview;", "analyzer", "Lcom/licenta/postureimprover/domain/FrameAnalyzer;", "executor", "Ljava/util/concurrent/ExecutorService;", "(Landroidx/camera/core/CameraSelector;Landroidx/camera/lifecycle/ProcessCameraProvider;Landroidx/camera/core/Preview;Lcom/licenta/postureimprover/domain/FrameAnalyzer;Ljava/util/concurrent/ExecutorService;)V", "getAnalyzer", "()Lcom/licenta/postureimprover/domain/FrameAnalyzer;", "setAnalyzer", "(Lcom/licenta/postureimprover/domain/FrameAnalyzer;)V", "getExecutor", "()Ljava/util/concurrent/ExecutorService;", "setExecutor", "(Ljava/util/concurrent/ExecutorService;)V", "getPreview", "()Landroidx/camera/core/Preview;", "setPreview", "(Landroidx/camera/core/Preview;)V", "getProvider", "()Landroidx/camera/lifecycle/ProcessCameraProvider;", "setProvider", "(Landroidx/camera/lifecycle/ProcessCameraProvider;)V", "getSelector", "()Landroidx/camera/core/CameraSelector;", "setSelector", "(Landroidx/camera/core/CameraSelector;)V", "changeSelector", "", "cameraSelector", "getImageAnalysis", "Landroidx/camera/core/ImageAnalysis;", "app_debug"})
public final class CameraViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private androidx.camera.core.CameraSelector selector;
    @org.jetbrains.annotations.NotNull()
    private androidx.camera.lifecycle.ProcessCameraProvider provider;
    @org.jetbrains.annotations.NotNull()
    private androidx.camera.core.Preview preview;
    @org.jetbrains.annotations.NotNull()
    private com.licenta.postureimprover.domain.FrameAnalyzer analyzer;
    @org.jetbrains.annotations.NotNull()
    private java.util.concurrent.ExecutorService executor;
    
    @javax.inject.Inject()
    public CameraViewModel(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.CameraSelector selector, @org.jetbrains.annotations.NotNull()
    androidx.camera.lifecycle.ProcessCameraProvider provider, @org.jetbrains.annotations.NotNull()
    androidx.camera.core.Preview preview, @org.jetbrains.annotations.NotNull()
    com.licenta.postureimprover.domain.FrameAnalyzer analyzer, @org.jetbrains.annotations.NotNull()
    java.util.concurrent.ExecutorService executor) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.camera.core.CameraSelector getSelector() {
        return null;
    }
    
    public final void setSelector(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.CameraSelector p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.camera.lifecycle.ProcessCameraProvider getProvider() {
        return null;
    }
    
    public final void setProvider(@org.jetbrains.annotations.NotNull()
    androidx.camera.lifecycle.ProcessCameraProvider p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.camera.core.Preview getPreview() {
        return null;
    }
    
    public final void setPreview(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.Preview p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.licenta.postureimprover.domain.FrameAnalyzer getAnalyzer() {
        return null;
    }
    
    public final void setAnalyzer(@org.jetbrains.annotations.NotNull()
    com.licenta.postureimprover.domain.FrameAnalyzer p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.concurrent.ExecutorService getExecutor() {
        return null;
    }
    
    public final void setExecutor(@org.jetbrains.annotations.NotNull()
    java.util.concurrent.ExecutorService p0) {
    }
    
    public final void changeSelector(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.CameraSelector cameraSelector) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.camera.core.ImageAnalysis getImageAnalysis() {
        return null;
    }
}
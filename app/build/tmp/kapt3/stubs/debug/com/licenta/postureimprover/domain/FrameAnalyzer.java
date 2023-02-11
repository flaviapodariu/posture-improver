package com.licenta.postureimprover.domain;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0017J&\u0010\u000f\u001a\u00020\f*\u00020\u00002\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u0006\u0010\u0014\u001a\u00020\u0015J&\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011*\u00020\u00002\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\u001a\u0010\u001a\u001a\u00020\u0012*\u00020\u00002\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/licenta/postureimprover/domain/FrameAnalyzer;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "options", "Lcom/google/mlkit/vision/pose/accurate/AccuratePoseDetectorOptions;", "analyze", "", "imageProxy", "Landroidx/camera/core/ImageProxy;", "drawResults", "map", "", "", "Landroid/graphics/PointF;", "image", "Lcom/google/mlkit/vision/common/InputImage;", "landmarksToPositions", "pose", "", "Lcom/google/mlkit/vision/pose/PoseLandmark;", "landmarksToString", "landmarks", "app_debug"})
public final class FrameAnalyzer implements androidx.camera.core.ImageAnalysis.Analyzer {
    private final com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions options = null;
    public android.content.Context context;
    
    @javax.inject.Inject()
    public FrameAnalyzer() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public final void setContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    @androidx.annotation.OptIn(markerClass = {androidx.camera.core.ExperimentalGetImage.class})
    @java.lang.Override()
    public void analyze(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageProxy imageProxy) {
    }
    
    private final java.lang.String landmarksToString(com.licenta.postureimprover.domain.FrameAnalyzer $this$landmarksToString, java.util.List<? extends com.google.mlkit.vision.pose.PoseLandmark> landmarks) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, android.graphics.PointF> landmarksToPositions(com.licenta.postureimprover.domain.FrameAnalyzer $this$landmarksToPositions, java.util.List<? extends com.google.mlkit.vision.pose.PoseLandmark> pose) {
        return null;
    }
    
    public final void drawResults(@org.jetbrains.annotations.NotNull()
    com.licenta.postureimprover.domain.FrameAnalyzer $this$drawResults, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends android.graphics.PointF> map, @org.jetbrains.annotations.NotNull()
    com.google.mlkit.vision.common.InputImage image) {
    }
}
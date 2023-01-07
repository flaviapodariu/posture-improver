package com.licenta.postureimprover.domain;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0017J(\u0010\t\u001a\u00020\u0006*\u00020\u00002\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J&\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b*\u00020\u00002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/licenta/postureimprover/domain/FrameAnalyzer;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "()V", "options", "Lcom/google/mlkit/vision/pose/accurate/AccuratePoseDetectorOptions;", "analyze", "", "imageProxy", "Landroidx/camera/core/ImageProxy;", "drawResults", "map", "", "", "Landroid/graphics/PointF;", "image", "Lcom/google/mlkit/vision/common/InputImage;", "landmarksToPositions", "pose", "", "Lcom/google/mlkit/vision/pose/PoseLandmark;", "app_debug"})
public final class FrameAnalyzer implements androidx.camera.core.ImageAnalysis.Analyzer {
    private final com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions options = null;
    
    @javax.inject.Inject()
    public FrameAnalyzer() {
        super();
    }
    
    @androidx.annotation.OptIn(markerClass = {androidx.camera.core.ExperimentalGetImage.class})
    @java.lang.Override()
    public void analyze(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageProxy imageProxy) {
    }
    
    private final java.util.Map<java.lang.String, android.graphics.PointF> landmarksToPositions(com.licenta.postureimprover.domain.FrameAnalyzer $this$landmarksToPositions, java.util.List<? extends com.google.mlkit.vision.pose.PoseLandmark> pose) {
        return null;
    }
    
    private final void drawResults(com.licenta.postureimprover.domain.FrameAnalyzer $this$drawResults, java.util.Map<java.lang.String, ? extends android.graphics.PointF> map, com.google.mlkit.vision.common.InputImage image) {
    }
}
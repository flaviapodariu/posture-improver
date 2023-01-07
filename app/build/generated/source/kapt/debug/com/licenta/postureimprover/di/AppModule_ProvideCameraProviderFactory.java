// Generated by Dagger (https://dagger.dev).
package com.licenta.postureimprover.di;

import android.app.Application;
import androidx.camera.lifecycle.ProcessCameraProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideCameraProviderFactory implements Factory<ProcessCameraProvider> {
  private final Provider<Application> appProvider;

  public AppModule_ProvideCameraProviderFactory(Provider<Application> appProvider) {
    this.appProvider = appProvider;
  }

  @Override
  public ProcessCameraProvider get() {
    return provideCameraProvider(appProvider.get());
  }

  public static AppModule_ProvideCameraProviderFactory create(Provider<Application> appProvider) {
    return new AppModule_ProvideCameraProviderFactory(appProvider);
  }

  public static ProcessCameraProvider provideCameraProvider(Application app) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCameraProvider(app));
  }
}

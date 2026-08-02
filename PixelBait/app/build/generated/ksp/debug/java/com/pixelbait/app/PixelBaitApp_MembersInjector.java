package com.pixelbait.app;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class PixelBaitApp_MembersInjector implements MembersInjector<PixelBaitApp> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public PixelBaitApp_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<PixelBaitApp> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new PixelBaitApp_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(PixelBaitApp instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.pixelbait.app.PixelBaitApp.workerFactory")
  public static void injectWorkerFactory(PixelBaitApp instance, HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}

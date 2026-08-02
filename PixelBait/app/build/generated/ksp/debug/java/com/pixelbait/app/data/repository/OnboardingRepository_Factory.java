package com.pixelbait.app.data.repository;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class OnboardingRepository_Factory implements Factory<OnboardingRepository> {
  private final Provider<Context> contextProvider;

  public OnboardingRepository_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OnboardingRepository get() {
    return newInstance(contextProvider.get());
  }

  public static OnboardingRepository_Factory create(Provider<Context> contextProvider) {
    return new OnboardingRepository_Factory(contextProvider);
  }

  public static OnboardingRepository newInstance(Context context) {
    return new OnboardingRepository(context);
  }
}

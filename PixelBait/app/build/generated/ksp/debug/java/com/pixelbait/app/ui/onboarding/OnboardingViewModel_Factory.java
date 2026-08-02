package com.pixelbait.app.ui.onboarding;

import com.pixelbait.app.core.security.SecureStorage;
import com.pixelbait.app.data.repository.OnboardingRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class OnboardingViewModel_Factory implements Factory<OnboardingViewModel> {
  private final Provider<OnboardingRepository> onboardingRepositoryProvider;

  private final Provider<SecureStorage> secureStorageProvider;

  public OnboardingViewModel_Factory(Provider<OnboardingRepository> onboardingRepositoryProvider,
      Provider<SecureStorage> secureStorageProvider) {
    this.onboardingRepositoryProvider = onboardingRepositoryProvider;
    this.secureStorageProvider = secureStorageProvider;
  }

  @Override
  public OnboardingViewModel get() {
    return newInstance(onboardingRepositoryProvider.get(), secureStorageProvider.get());
  }

  public static OnboardingViewModel_Factory create(
      Provider<OnboardingRepository> onboardingRepositoryProvider,
      Provider<SecureStorage> secureStorageProvider) {
    return new OnboardingViewModel_Factory(onboardingRepositoryProvider, secureStorageProvider);
  }

  public static OnboardingViewModel newInstance(OnboardingRepository onboardingRepository,
      SecureStorage secureStorage) {
    return new OnboardingViewModel(onboardingRepository, secureStorage);
  }
}

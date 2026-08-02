package com.pixelbait.app;

import com.pixelbait.app.data.repository.OnboardingRepository;
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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<OnboardingRepository> onboardingRepositoryProvider;

  public MainActivity_MembersInjector(Provider<OnboardingRepository> onboardingRepositoryProvider) {
    this.onboardingRepositoryProvider = onboardingRepositoryProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<OnboardingRepository> onboardingRepositoryProvider) {
    return new MainActivity_MembersInjector(onboardingRepositoryProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectOnboardingRepository(instance, onboardingRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.pixelbait.app.MainActivity.onboardingRepository")
  public static void injectOnboardingRepository(MainActivity instance,
      OnboardingRepository onboardingRepository) {
    instance.onboardingRepository = onboardingRepository;
  }
}

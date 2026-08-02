package com.pixelbait.app.di;

import com.pixelbait.app.core.network.VirusTotalApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
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
public final class NetworkModule_ProvideVirusTotalApiFactory implements Factory<VirusTotalApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideVirusTotalApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public VirusTotalApi get() {
    return provideVirusTotalApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideVirusTotalApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideVirusTotalApiFactory(retrofitProvider);
  }

  public static VirusTotalApi provideVirusTotalApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideVirusTotalApi(retrofit));
  }
}

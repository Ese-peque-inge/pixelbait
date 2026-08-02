package com.pixelbait.app.data.repository;

import com.pixelbait.app.core.network.VirusTotalApi;
import com.pixelbait.app.core.security.SecureStorage;
import com.pixelbait.app.data.local.HistoryDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ScanRepository_Factory implements Factory<ScanRepository> {
  private final Provider<VirusTotalApi> apiProvider;

  private final Provider<SecureStorage> secureStorageProvider;

  private final Provider<HistoryDao> historyDaoProvider;

  public ScanRepository_Factory(Provider<VirusTotalApi> apiProvider,
      Provider<SecureStorage> secureStorageProvider, Provider<HistoryDao> historyDaoProvider) {
    this.apiProvider = apiProvider;
    this.secureStorageProvider = secureStorageProvider;
    this.historyDaoProvider = historyDaoProvider;
  }

  @Override
  public ScanRepository get() {
    return newInstance(apiProvider.get(), secureStorageProvider.get(), historyDaoProvider.get());
  }

  public static ScanRepository_Factory create(Provider<VirusTotalApi> apiProvider,
      Provider<SecureStorage> secureStorageProvider, Provider<HistoryDao> historyDaoProvider) {
    return new ScanRepository_Factory(apiProvider, secureStorageProvider, historyDaoProvider);
  }

  public static ScanRepository newInstance(VirusTotalApi api, SecureStorage secureStorage,
      HistoryDao historyDao) {
    return new ScanRepository(api, secureStorage, historyDao);
  }
}

package com.pixelbait.app.ui.scanner;

import com.pixelbait.app.data.repository.ScanRepository;
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
public final class ScannerViewModel_Factory implements Factory<ScannerViewModel> {
  private final Provider<ScanRepository> scanRepositoryProvider;

  public ScannerViewModel_Factory(Provider<ScanRepository> scanRepositoryProvider) {
    this.scanRepositoryProvider = scanRepositoryProvider;
  }

  @Override
  public ScannerViewModel get() {
    return newInstance(scanRepositoryProvider.get());
  }

  public static ScannerViewModel_Factory create(Provider<ScanRepository> scanRepositoryProvider) {
    return new ScannerViewModel_Factory(scanRepositoryProvider);
  }

  public static ScannerViewModel newInstance(ScanRepository scanRepository) {
    return new ScannerViewModel(scanRepository);
  }
}

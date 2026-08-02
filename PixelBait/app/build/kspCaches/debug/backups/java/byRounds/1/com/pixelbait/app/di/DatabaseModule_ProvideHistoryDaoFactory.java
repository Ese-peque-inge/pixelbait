package com.pixelbait.app.di;

import com.pixelbait.app.data.local.AppDatabase;
import com.pixelbait.app.data.local.HistoryDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideHistoryDaoFactory implements Factory<HistoryDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideHistoryDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public HistoryDao get() {
    return provideHistoryDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideHistoryDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideHistoryDaoFactory(dbProvider);
  }

  public static HistoryDao provideHistoryDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideHistoryDao(db));
  }
}

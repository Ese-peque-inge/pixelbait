package com.pixelbait.app.data.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class HistoryCleanupWorker_AssistedFactory_Impl implements HistoryCleanupWorker_AssistedFactory {
  private final HistoryCleanupWorker_Factory delegateFactory;

  HistoryCleanupWorker_AssistedFactory_Impl(HistoryCleanupWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public HistoryCleanupWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<HistoryCleanupWorker_AssistedFactory> create(
      HistoryCleanupWorker_Factory delegateFactory) {
    return InstanceFactory.create(new HistoryCleanupWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<HistoryCleanupWorker_AssistedFactory> createFactoryProvider(
      HistoryCleanupWorker_Factory delegateFactory) {
    return InstanceFactory.create(new HistoryCleanupWorker_AssistedFactory_Impl(delegateFactory));
  }
}

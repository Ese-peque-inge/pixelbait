package com.pixelbait.app.data.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.pixelbait.app.core.notifications.NotificationHelper;
import com.pixelbait.app.data.local.HistoryDao;
import dagger.internal.DaggerGenerated;
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
public final class HistoryCleanupWorker_Factory {
  private final Provider<HistoryDao> historyDaoProvider;

  private final Provider<NotificationHelper> notificationHelperProvider;

  public HistoryCleanupWorker_Factory(Provider<HistoryDao> historyDaoProvider,
      Provider<NotificationHelper> notificationHelperProvider) {
    this.historyDaoProvider = historyDaoProvider;
    this.notificationHelperProvider = notificationHelperProvider;
  }

  public HistoryCleanupWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params, historyDaoProvider.get(), notificationHelperProvider.get());
  }

  public static HistoryCleanupWorker_Factory create(Provider<HistoryDao> historyDaoProvider,
      Provider<NotificationHelper> notificationHelperProvider) {
    return new HistoryCleanupWorker_Factory(historyDaoProvider, notificationHelperProvider);
  }

  public static HistoryCleanupWorker newInstance(Context context, WorkerParameters params,
      HistoryDao historyDao, NotificationHelper notificationHelper) {
    return new HistoryCleanupWorker(context, params, historyDao, notificationHelper);
  }
}

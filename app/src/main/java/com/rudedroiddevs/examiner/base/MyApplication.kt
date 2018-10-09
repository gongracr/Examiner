package com.rudedroiddevs.examiner.base

import android.app.Application
import com.rudedroiddevs.examiner.base.dagger.ApplicationComponent
import com.rudedroiddevs.examiner.base.dagger.ApplicationModule
import com.rudedroiddevs.examiner.base.dagger.DaggerApplicationComponent

class MyApplication : Application() {

  val applicationComponent: ApplicationComponent by lazy {
    DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()
  }

  override fun onCreate() {
    super.onCreate()
    applicationComponent.inject(this)
  }
}
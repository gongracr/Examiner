package com.rudedroiddevs.examiner.base.dagger

import android.app.Application
import android.content.res.Resources
import com.rudedroiddevs.examiner.base.dagger.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {

  @ApplicationScope
  @Provides
  fun providesApplication(): Application = application

  @ApplicationScope
  @Provides
  fun providesResources(): Resources = application.resources
}
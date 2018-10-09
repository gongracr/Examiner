package com.rudedroiddevs.examiner.base.dagger

import android.app.Application
import android.content.res.Resources
import com.rudedroiddevs.examiner.base.MyApplication
import com.rudedroiddevs.examiner.base.dagger.scopes.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

  fun inject(myApplication: MyApplication)

  val application: Application

  val resources: Resources
}


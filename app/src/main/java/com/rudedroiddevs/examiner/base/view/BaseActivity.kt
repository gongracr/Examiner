package com.rudedroiddevs.examiner.base.view

import androidx.appcompat.app.AppCompatActivity
import com.rudedroiddevs.examiner.base.MyApplication
import com.rudedroiddevs.examiner.base.dagger.ApplicationComponent

abstract class BaseActivity : AppCompatActivity() {

  protected val applicationComponent: ApplicationComponent
    get() = (application as MyApplication).applicationComponent
}
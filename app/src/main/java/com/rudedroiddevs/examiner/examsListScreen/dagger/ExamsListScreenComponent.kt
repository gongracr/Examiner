package com.rudedroiddevs.examiner.examsListScreen.dagger

import com.rudedroiddevs.examiner.base.dagger.ApplicationComponent
import com.rudedroiddevs.examiner.base.dagger.scopes.ActivityScope
import com.rudedroiddevs.examiner.examsListScreen.view.ExamsListActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ExamsListScreenModule::class])
interface ExamsListScreenComponent {

  fun inject(examsListActivity: ExamsListActivity)
}
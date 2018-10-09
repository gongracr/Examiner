package com.rudedroiddevs.examiner.examScreen.dagger

import com.rudedroiddevs.examiner.base.dagger.ApplicationComponent
import com.rudedroiddevs.examiner.base.dagger.scopes.ActivityScope
import com.rudedroiddevs.examiner.examScreen.view.ExamActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ExamScreenModule::class])
interface ExamScreenComponent {

  fun inject(examActivity: ExamActivity)
}
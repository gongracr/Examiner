package com.rudedroiddevs.examiner.examScreen.dagger

import com.rudedroiddevs.examiner.base.dagger.scopes.ActivityScope
import com.rudedroiddevs.examiner.examScreen.view.ExamScreenView
import com.rudedroiddevs.examiner.examScreen.interactor.ExamScreenInteractor
import com.rudedroiddevs.examiner.examScreen.interactor.ExamScreenInteractorImpl
import com.rudedroiddevs.examiner.examScreen.presenter.ExamPresenter
import com.rudedroiddevs.examiner.examScreen.presenter.ExamPresenterImpl
import com.rudedroiddevs.examiner.examsListScreen.mapper.ExamsListModelMapper
import dagger.Module
import dagger.Provides

@Module
class ExamScreenModule(private val examScreenView: ExamScreenView) {

  @ActivityScope
  @Provides
  fun providesExamScreenView(): ExamScreenView = examScreenView

  @ActivityScope
  @Provides
  fun providesExamScreenPresenter(
      examScreenPresenter: ExamPresenterImpl): ExamPresenter = examScreenPresenter

  @ActivityScope
  @Provides
  fun providesExamsListScreenInteractor(
      examScreenInteractor: ExamScreenInteractorImpl): ExamScreenInteractor = examScreenInteractor

  @ActivityScope
  @Provides
  fun providesExamsListModelMapper() = ExamsListModelMapper()
}
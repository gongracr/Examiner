package com.rudedroiddevs.examiner.examsListScreen.dagger

import com.rudedroiddevs.examiner.base.dagger.scopes.ActivityScope
import com.rudedroiddevs.examiner.examsListScreen.interactor.ExamsListScreenInteractor
import com.rudedroiddevs.examiner.examsListScreen.interactor.ExamsListScreenInteractorImpl
import com.rudedroiddevs.examiner.examsListScreen.mapper.ExamsListModelMapper
import com.rudedroiddevs.examiner.examsListScreen.presenter.ExamsListPresenter
import com.rudedroiddevs.examiner.examsListScreen.presenter.ExamsListPresenterImpl
import com.rudedroiddevs.examiner.examsListScreen.view.ExamsListScreenView
import dagger.Module
import dagger.Provides

@Module
class ExamsListScreenModule(private val examsListScreenView: ExamsListScreenView) {

  @ActivityScope
  @Provides
  fun providesExamsListScreenView()
      : ExamsListScreenView = examsListScreenView

  @ActivityScope
  @Provides
  fun providesExamsListScreenPresenter(examsListScreenPresenter: ExamsListPresenterImpl)
      : ExamsListPresenter = examsListScreenPresenter

  @ActivityScope
  @Provides
  fun providesExamsListScreenInteractor(examsListScreenInteractor: ExamsListScreenInteractorImpl)
      : ExamsListScreenInteractor = examsListScreenInteractor

  @ActivityScope
  @Provides
  fun providesExamsListModelMapper() = ExamsListModelMapper()
}
package com.rudedroiddevs.examiner.examScreen.presenter

import com.rudedroiddevs.examiner.examScreen.view.ExamScreenView
import com.rudedroiddevs.examiner.examScreen.mapper.ExamModelMapper
import com.rudedroiddevs.examiner.examsListScreen.interactor.ExamsListScreenInteractor
import javax.inject.Inject

class ExamPresenterImpl @Inject constructor(
    private val examScreenView: ExamScreenView,
    private val examScreenInteractor: ExamsListScreenInteractor,
    private val examModelMapper: ExamModelMapper) : ExamPresenter{

  override fun viewCreated() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun viewDestroyed() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}

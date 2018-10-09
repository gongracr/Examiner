package com.rudedroiddevs.examiner.examScreen.presenter

import com.rudedroiddevs.examiner.examScreen.interactor.ExamScreenInteractor
import com.rudedroiddevs.examiner.examScreen.mapper.ExamModelMapper
import com.rudedroiddevs.examiner.examScreen.view.ExamScreenView
import javax.inject.Inject

class ExamPresenterImpl @Inject constructor(
    private val examScreenView: ExamScreenView,
    private val examScreenInteractor: ExamScreenInteractor,
    private val examModelMapper: ExamModelMapper) : ExamPresenter {

  override fun viewCreated() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun viewDestroyed() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}

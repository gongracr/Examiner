package com.rudedroiddevs.examiner.examsListScreen.presenter

interface ExamsListPresenter {

  fun viewCreated()

  fun viewDestroyed()
  
  fun onExamClicked(pos: Int)
}
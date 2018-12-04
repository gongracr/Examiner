package com.rudedroiddevs.examiner.examsListScreen.presenter

import android.content.Context

interface ExamsListPresenter {

  fun viewCreated(context: Context)

  fun viewDestroyed()
  
  fun viewResumed(context: Context)

  fun onExamClicked(pos: Int)
}
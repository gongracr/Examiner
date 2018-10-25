package com.rudedroiddevs.examiner.examScreen.presenter

import android.app.Activity

interface ExamPresenter {

  fun viewCreated(activity: Activity)

  fun viewDestroyed()

  fun onCorrectExamClicked()

}

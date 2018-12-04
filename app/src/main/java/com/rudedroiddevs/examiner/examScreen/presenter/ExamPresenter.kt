package com.rudedroiddevs.examiner.examScreen.presenter

import android.content.Context
import com.rudedroiddevs.examiner.examScreen.view.ExamActivity

interface ExamPresenter {

  fun viewCreated(context: Context, examPosition: Int)

  fun viewDestroyed(context: Context)

  fun onCorrectExamClicked(context: Context)

  fun onAnswerSelected()

  fun onBackPressed(context: Context)

  fun onAbandonExamClicked(context: Context)

  fun viewPaused(context: Context)

}

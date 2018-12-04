package com.rudedroiddevs.examiner.examScreen.view

import com.rudedroiddevs.examiner.examScreen.model.ExamModel

interface ExamScreenView {

  fun displayExamModel(examModel: ExamModel)

  fun showCorrectedExam(totalCorrect: Int, totalWrong: Int, totalEmpty: Int, numCorrectedTimes: Int)

  fun showExitDialog()

  fun goToExamsListActivity()
}

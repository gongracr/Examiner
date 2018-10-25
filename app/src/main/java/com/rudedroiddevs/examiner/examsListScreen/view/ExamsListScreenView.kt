package com.rudedroiddevs.examiner.examsListScreen.view

import com.rudedroiddevs.examiner.examsListScreen.model.ExamsListModel

interface ExamsListScreenView {

  fun displayExamListModel(examsListModel: ExamsListModel)

  fun goToExamActivity(pos: Int)
}
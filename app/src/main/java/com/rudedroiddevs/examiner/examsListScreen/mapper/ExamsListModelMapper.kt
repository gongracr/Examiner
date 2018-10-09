package com.rudedroiddevs.examiner.examsListScreen.mapper

import com.rudedroiddevs.examiner.base.mapper.BaseMapper
import com.rudedroiddevs.examiner.examsListScreen.model.ExamsListModel
import com.rudedroiddevs.examiner.pojomodel.Exam
import javax.inject.Inject

class ExamsListModelMapper @Inject constructor() : BaseMapper<List<Exam>, ExamsListModel> {
  override fun mapDataModelToViewModel(dataModel: List<Exam>): ExamsListModel {
    val examTitlesList = ArrayList<String>()
    dataModel.forEach { exam -> examTitlesList.add(exam.examName ?: "") }
    return ExamsListModel(examTitlesList)
  }

}

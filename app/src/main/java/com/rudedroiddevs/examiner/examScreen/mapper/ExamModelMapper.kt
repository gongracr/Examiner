package com.rudedroiddevs.examiner.examScreen.mapper

import com.rudedroiddevs.examiner.base.mapper.BaseMapper
import com.rudedroiddevs.examiner.examScreen.model.ExamModel
import com.rudedroiddevs.examiner.pojomodel.Exam
import javax.inject.Inject

class ExamModelMapper @Inject constructor() : BaseMapper<Exam, ExamModel> {
  override fun mapDataModelToViewModel(dataModel: Exam): ExamModel = ExamModel(
      dataModel.examName ?: "", dataModel.questionsList ?: emptyList())

}

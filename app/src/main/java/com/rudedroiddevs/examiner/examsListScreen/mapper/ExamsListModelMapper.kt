package com.rudedroiddevs.examiner.examsListScreen.mapper

import android.content.Context
import com.rudedroiddevs.examiner.base.MyApplication
import com.rudedroiddevs.examiner.base.mapper.BaseMapper
import com.rudedroiddevs.examiner.examsListScreen.model.ExamsListModel
import com.rudedroiddevs.examiner.pojomodel.Exam
import com.rudedroiddevs.examiner.utils.SHARED_PREFS
import com.rudedroiddevs.examiner.utils.getTimesCorrected
import javax.inject.Inject

class ExamsListModelMapper @Inject constructor() : BaseMapper<List<Exam>, ExamsListModel> {
  override fun mapDataModelToViewModel(dataModel: List<Exam>): ExamsListModel {
    val examTitlesList = ArrayList<String>()
    var text = ""
    dataModel.forEach { exam ->
      run {
        text = exam.examName + "\n" + "Veces corregido: " + getTimesCorrected(exam,
            MyApplication.instance.applicationContext.getSharedPreferences(
                SHARED_PREFS, Context.MODE_PRIVATE))
      }
      examTitlesList.add(text)
    }
    return ExamsListModel(examTitlesList)
  }
}
package com.rudedroiddevs.examiner.examScreen.interactor

import android.app.Application
import com.rudedroiddevs.examiner.pojomodel.Exam
import com.rudedroiddevs.examiner.utils.loadExam
import io.reactivex.Observable
import javax.inject.Inject

class ExamScreenInteractorImpl @Inject constructor(
    private val application: Application) : ExamScreenInteractor {

  override fun getExamObservable(examPos: Int): Observable<Exam> {
    return Observable.just(loadExam(application, examPos))
  }

}

package com.rudedroiddevs.examiner.examsListScreen.interactor

import android.content.Context
import com.rudedroiddevs.examiner.pojomodel.Exam
import com.rudedroiddevs.examiner.utils.loadExams
import io.reactivex.Observable
import javax.inject.Inject

class ExamsListScreenInteractorImpl @Inject constructor(
    private val context: Context) : ExamsListScreenInteractor {

  override fun getExamsObservable(): Observable<List<Exam>> = Observable.just(loadExams(context))

}

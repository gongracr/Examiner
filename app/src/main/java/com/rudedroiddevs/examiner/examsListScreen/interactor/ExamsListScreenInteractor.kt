package com.rudedroiddevs.examiner.examsListScreen.interactor

import com.rudedroiddevs.examiner.pojomodel.Exam
import io.reactivex.Observable

interface ExamsListScreenInteractor {

  fun getExamsObservable(): Observable<List<Exam>>
}

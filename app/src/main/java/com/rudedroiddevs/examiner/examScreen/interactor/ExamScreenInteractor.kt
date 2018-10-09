package com.rudedroiddevs.examiner.examScreen.interactor

import com.rudedroiddevs.examiner.pojomodel.Exam
import io.reactivex.Observable

interface ExamScreenInteractor {

  fun getExamObservable(examPos: Int): Observable<Exam>

}

package com.rudedroiddevs.examiner.examsListScreen.interactor

import android.app.Application
import com.rudedroiddevs.examiner.utils.loadExams
import io.reactivex.Observable
import javax.inject.Inject

class ExamsListScreenInteractorImpl @Inject constructor(
    private val application: Application) : ExamsListScreenInteractor {

  override fun getExamsObservable() = Observable.just(loadExams(application))

}

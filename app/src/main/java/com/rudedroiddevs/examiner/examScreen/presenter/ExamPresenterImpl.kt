package com.rudedroiddevs.examiner.examScreen.presenter

import android.app.Activity
import android.util.Log
import com.rudedroiddevs.examiner.examScreen.interactor.ExamScreenInteractor
import com.rudedroiddevs.examiner.examScreen.mapper.ExamModelMapper
import com.rudedroiddevs.examiner.examScreen.view.ExamScreenView
import com.rudedroiddevs.examiner.pojomodel.Exam
import com.rudedroiddevs.examiner.utils.NUM_EXAM
import com.rudedroiddevs.examiner.utils.getExtra
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExamPresenterImpl @Inject constructor(
    private val examScreenView: ExamScreenView,
    private val examScreenInteractor: ExamScreenInteractor,
    private val examModelMapper: ExamModelMapper) : ExamPresenter {

  companion object {
    private val TAG = ExamPresenterImpl::class.java.simpleName
  }

  private lateinit var exam: Exam
  private val disposables = CompositeDisposable()

  override fun viewCreated(activity: Activity) {
    val examPos = activity.getExtra(NUM_EXAM) ?: 0
    subscribeExamEvent(examPos)
  }

  override fun viewDestroyed() {
    disposables.dispose()
  }

  private fun subscribeExamEvent(examPos: Int) {
    disposables.add(examScreenInteractor.getExamObservable(examPos)
        .map {
          exam = it
          examModelMapper.mapDataModelToViewModel(it)
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          examScreenView.displayExamModel(it)
        }, {
          Log.d(TAG, "Error getting selected Exam", it)
        }))
  }

  override fun onCorrectExamClicked() {
    var totalCorrect = 0
    var totalWrong = 0
    var totalEmpty = 0
    exam.questionsList?.forEach { q ->
      run {
        when (q.selectedAnswer) {
          0 -> {
            if (q.correctAnswer.toLowerCase() == "a") {
              totalCorrect++
            } else {
              totalWrong ++
            }
          }
          1 -> {
            if (q.correctAnswer.toLowerCase() == "b") {
              totalCorrect++
            } else {
              totalWrong ++
            }
          }
          2 -> {
            if (q.correctAnswer.toLowerCase() == "c") {
              totalCorrect++
            } else {
              totalWrong ++
            }
          }
          3 -> {
            if (q.correctAnswer.toLowerCase() == "d") {
              totalCorrect++
            } else {
              totalWrong ++
            }
          }
          else -> {
            totalEmpty ++
          }
        }
      }
    }
    examScreenView.showCorrectedExam(totalCorrect, totalWrong, totalEmpty)
  }
}
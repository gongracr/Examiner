package com.rudedroiddevs.examiner.examScreen.presenter

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.rudedroiddevs.examiner.examScreen.interactor.ExamScreenInteractor
import com.rudedroiddevs.examiner.examScreen.mapper.ExamModelMapper
import com.rudedroiddevs.examiner.examScreen.view.ExamScreenView
import com.rudedroiddevs.examiner.pojomodel.Exam
import com.rudedroiddevs.examiner.utils.SHARED_PREFS
import com.rudedroiddevs.examiner.utils.SharedPrefsUtils
import com.rudedroiddevs.examiner.utils.SharedPrefsUtils.Companion.loadPendingExam
import com.rudedroiddevs.examiner.utils.SharedPrefsUtils.Companion.removePendingExam
import com.rudedroiddevs.examiner.utils.updateExamCorrected
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
  private lateinit var prefs: SharedPreferences
  private val disposables = CompositeDisposable()
  private var shouldSaveExam = false
  private var isCorrected = false

  override fun viewCreated(context: Context, examPosition: Int) {
    val pendingExam = SharedPrefsUtils.loadPendingExam(context)
    if (pendingExam != null) {
      exam = pendingExam
      examScreenView.displayExamModel(examModelMapper.mapDataModelToViewModel(exam))
    } else {
      subscribeExamEvent(examPosition)
    }
    prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
  }

  override fun viewDestroyed(context: Context) {
    disposables.dispose()
  }

  override fun viewPaused(context: Context) {
    if (!isCorrected && shouldSaveExam)
      SharedPrefsUtils.savePendingExam(context, exam)
  }

  override fun onBackPressed(context: Context) {
    if (loadPendingExam(context) != null || (!isCorrected && shouldSaveExam)) {
      examScreenView.showExitDialog()
    } else {
      examScreenView.goToExamsListActivity()
    }
  }

  override fun onAnswerSelected() {
    shouldSaveExam = true
  }

  override fun onAbandonExamClicked(context: Context) {
    removeExamFromPrefs(context)
    examScreenView.goToExamsListActivity()
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

  override fun onCorrectExamClicked(context: Context) {
    isCorrected = true
    var totalCorrect = 0
    var totalWrong = 0
    var totalEmpty = 0
    val numCorrectedTimes = updateExamCorrected(exam, prefs)
    removeExamFromPrefs(context)

    exam.questionsList?.forEach { q ->
      run {
        when (q.selectedAnswer) {
          0 -> {
            if (q.correctAnswer.toLowerCase() == "a") {
              totalCorrect++
            } else {
              totalWrong++
            }
          }
          1 -> {
            if (q.correctAnswer.toLowerCase() == "b") {
              totalCorrect++
            } else {
              totalWrong++
            }
          }
          2 -> {
            if (q.correctAnswer.toLowerCase() == "c") {
              totalCorrect++
            } else {
              totalWrong++
            }
          }
          3 -> {
            if (q.correctAnswer.toLowerCase() == "d") {
              totalCorrect++
            } else {
              totalWrong++
            }
          }
          else -> {
            totalEmpty++
          }
        }
      }
    }
    examScreenView.showCorrectedExam(totalCorrect, totalWrong, totalEmpty, numCorrectedTimes)
  }

  private fun removeExamFromPrefs(context: Context) {
    shouldSaveExam = false
    removePendingExam(context)
  }

}
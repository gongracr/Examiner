package com.rudedroiddevs.examiner.examsListScreen.presenter

import android.content.Context
import android.util.Log
import com.rudedroiddevs.examiner.examsListScreen.interactor.ExamsListScreenInteractor
import com.rudedroiddevs.examiner.examsListScreen.mapper.ExamsListModelMapper
import com.rudedroiddevs.examiner.examsListScreen.view.ExamsListScreenView
import com.rudedroiddevs.examiner.utils.SharedPrefsUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExamsListPresenterImpl @Inject constructor(
    private val examsListScreenView: ExamsListScreenView,
    private val examsListScreenInteractor: ExamsListScreenInteractor,
    private val examsListModelMapper: ExamsListModelMapper) : ExamsListPresenter {

  companion object {
    private val TAG = ExamsListPresenterImpl::class.java.simpleName
  }

  private val disposables = CompositeDisposable()

  override fun viewCreated(context: Context) {
    if (SharedPrefsUtils.loadPendingExam(context) != null)
      examsListScreenView.goToExamActivity(0)
  }

  override fun viewResumed(context: Context) {
    subscribeExamsEvents()
  }

  override fun viewDestroyed() {

    disposables.dispose()
  }

  override fun onExamClicked(pos: Int) {
    examsListScreenView.goToExamActivity(pos)
  }

  private fun subscribeExamsEvents() {
    disposables.add(examsListScreenInteractor.getExamsObservable()
        .map {
          examsListModelMapper.mapDataModelToViewModel(it)
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          examsListScreenView.displayExamListModel(it)
        }, {
          Log.d(TAG, "Error getting Exams titles", it)
        }))
  }
}
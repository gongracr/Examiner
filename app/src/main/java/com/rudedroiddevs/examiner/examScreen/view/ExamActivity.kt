package com.rudedroiddevs.examiner.examScreen.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.rudedroiddevs.examiner.R
import com.rudedroiddevs.examiner.base.view.BaseActivity
import com.rudedroiddevs.examiner.examScreen.dagger.DaggerExamScreenComponent
import com.rudedroiddevs.examiner.examScreen.dagger.ExamScreenModule
import com.rudedroiddevs.examiner.examScreen.model.ExamModel
import com.rudedroiddevs.examiner.examScreen.presenter.ExamPresenter
import com.rudedroiddevs.examiner.examsListScreen.view.ExamsListActivity
import com.rudedroiddevs.examiner.utils.NUM_EXAM
import com.rudedroiddevs.examiner.utils.getExtra
import kotlinx.android.synthetic.main.activity_exam_questions.*
import kotlinx.android.synthetic.main.question_solver_layout.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class ExamActivity : BaseActivity(), ExamScreenView {

  @Inject
  lateinit var presenter: ExamPresenter
  lateinit var examAdapter: QuestionsRecyclerAdapter
  lateinit var builder: AlertDialog.Builder

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_exam_questions)
    injectDependencies()
    questionsRecyclerView.layoutManager = LinearLayoutManager(this)
    builder = AlertDialog.Builder(this)
    examAdapter = QuestionsRecyclerAdapter(presenter)
    examAdapter.clickOnSolveExamListener = {
      presenter.onCorrectExamClicked(this)
    }
    questionsRecyclerView.adapter = examAdapter
    presenter.viewCreated(this, getExtra(NUM_EXAM) ?: 0)
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.viewDestroyed(this)
  }

  override fun onPause() {
    super.onPause()
    presenter.viewPaused(this)
  }

  override fun goToExamsListActivity() {
    startActivity(Intent(this, ExamsListActivity::class.java))
    finish()
  }

  override fun onBackPressed() {
    presenter.onBackPressed(this)
  }

  override fun showCorrectedExam(totalCorrect: Int, totalWrong: Int, totalEmpty: Int,
      numCorrectedTimes: Int) {
    totalCorrectAnswers.text = totalCorrect.toString()
    totalWrongAnswers.text = totalWrong.toString()
    totalEmptyAnswers.text = totalEmpty.toString()
    timesCorrected.text = numCorrectedTimes.toString()
    correctionSummary.visibility = View.VISIBLE
    examAdapter.correctionMode = true
    examAdapter.notifyDataSetChanged()
    questionsRecyclerView.smoothScrollToPosition(examAdapter.examModel.questions.size)
  }

  override fun showExitDialog() {
    builder.setTitle(getString(R.string.exit_dialog_title))
    builder.setMessage(getString(R.string.exit_dialog_body))
    builder.setPositiveButton("SI") { dialog, which ->
      presenter.onAbandonExamClicked(this)
    }
    builder.setNegativeButton("NO") { dialog, which ->
    }
    builder.create().show()
  }

  private fun injectDependencies() {
    DaggerExamScreenComponent.builder()
        .applicationComponent(applicationComponent)
        .examScreenModule(ExamScreenModule(this))
        .build()
        .inject(this)
  }

  override fun displayExamModel(examModel: ExamModel) {
    examTitleMain.text = examModel.examName
    examAdapter.examModel = examModel
    examAdapter.notifyDataSetChanged()
  }

}
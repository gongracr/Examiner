package com.rudedroiddevs.examiner.examScreen.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.rudedroiddevs.examiner.R
import com.rudedroiddevs.examiner.base.view.BaseActivity
import com.rudedroiddevs.examiner.examScreen.dagger.DaggerExamScreenComponent
import com.rudedroiddevs.examiner.examScreen.dagger.ExamScreenModule
import com.rudedroiddevs.examiner.examScreen.model.ExamModel
import com.rudedroiddevs.examiner.examScreen.presenter.ExamPresenter
import kotlinx.android.synthetic.main.activity_exam_questions.*
import kotlinx.android.synthetic.main.fragment_exams_list.*
import kotlinx.android.synthetic.main.question_solver_layout.*
import javax.inject.Inject

class ExamActivity : BaseActivity(), ExamScreenView {

  @Inject
  lateinit var presenter: ExamPresenter
  lateinit var examAdapter: QuestionsRecyclerAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_exam_questions)
    injectDependencies()
    questionsRecyclerView.layoutManager = LinearLayoutManager(this)
    examAdapter = QuestionsRecyclerAdapter()
    examAdapter.clickOnSolveExamListener = {
      presenter.onCorrectExamClicked()
    }
    questionsRecyclerView.adapter = examAdapter
    presenter.viewCreated(this)
  }

  override fun showCorrectedExam(totalCorrect: Int, totalWrong: Int, totalEmpty: Int) {
    totalCorrectAnswers.text = totalCorrect.toString()
    totalWrongAnswers.text = totalWrong.toString()
    totalEmptyAnswers.text = totalEmpty.toString()
    correctionSummary.visibility = View.VISIBLE
    examAdapter.correctionMode = true
    examAdapter.notifyDataSetChanged()
    questionsRecyclerView.smoothScrollToPosition(examAdapter.examModel.questions.size)
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
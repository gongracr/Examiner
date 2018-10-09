package com.rudedroiddevs.examiner.examScreen.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rudedroiddevs.examiner.R
import com.rudedroiddevs.examiner.base.view.BaseActivity
import com.rudedroiddevs.examiner.examScreen.dagger.DaggerExamScreenComponent
import com.rudedroiddevs.examiner.examScreen.dagger.ExamScreenModule
import com.rudedroiddevs.examiner.examScreen.presenter.ExamPresenter
import com.rudedroiddevs.examiner.pojomodel.Exam
import com.rudedroiddevs.examiner.utils.NUM_EXAM
import com.rudedroiddevs.examiner.utils.getExtra
import com.rudedroiddevs.examiner.utils.loadExam
import kotlinx.android.synthetic.main.activity_exam_questions.*
import javax.inject.Inject

class ExamActivity : BaseActivity(), ExamScreenView {

  @Inject
  lateinit var presenter: ExamPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val examPos = getExtra(NUM_EXAM) ?: 0
    val exam = loadExam(applicationContext, examPos)
    setContentView(R.layout.activity_exam_questions)
    injectDependencies()
    questionsRecyclerView.layoutManager = LinearLayoutManager(this)
    questionsRecyclerView.adapter = QuestionsRecyclerAdapter(exam)
    examTitleMain.text = exam?.examName
  }

  private fun injectDependencies() {
    DaggerExamScreenComponent.builder()
        .applicationComponent(applicationComponent)
        .examScreenModule(ExamScreenModule(this))
        .build()
        .inject(this)
  }

  override fun displayExamModel(exam: Exam) {

  }

}
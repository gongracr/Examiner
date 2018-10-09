package com.rudedroiddevs.examiner

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rudedroiddevs.examiner.base.view.BaseActivity
import com.rudedroiddevs.examiner.examScreen.dagger.ExamScreenModule
import com.rudedroiddevs.examiner.examScreen.view.ExamScreenView
import com.rudedroiddevs.examiner.pojomodel.Exam
import com.rudedroiddevs.examiner.utils.NUM_EXAM
import com.rudedroiddevs.examiner.utils.getExtra
import com.rudedroiddevs.examiner.utils.loadExam
import kotlinx.android.synthetic.main.activity_exam_questions.*

class ExamActivity : BaseActivity(), ExamScreenView {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val examPos: Int = getExtra(NUM_EXAM) ?: 0
    val exam: Exam? = loadExam(applicationContext, examPos)
    setContentView(R.layout.activity_exam_questions)
    injectDependencies()
    questionsRecyclerView.layoutManager = LinearLayoutManager(this)
    questionsRecyclerView.adapter = QuestionsRecyclerAdapter(exam)
    examTitleMain.text = exam?.examName
  }

  private fun injectDependencies() {
    DaggerMainScreenComponent.builder()
        .applicationComponent(applicationComponent)
        .mainScreenModule(ExamScreenModule(this))
        .build()
        .inject(this)
  }

  override fun displayExamModel(exam: Exam) {

  }

}
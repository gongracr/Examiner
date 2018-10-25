package com.rudedroiddevs.examiner.examScreen.view

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rudedroiddevs.examiner.R
import com.rudedroiddevs.examiner.examScreen.model.ExamModel
import com.rudedroiddevs.examiner.examScreen.view.QuestionsRecyclerAdapter.QuestionHolder
import com.rudedroiddevs.examiner.utils.inflate
import kotlinx.android.synthetic.main.question_recyclerview_item_row.view.*
import kotlinx.android.synthetic.main.question_solver_layout.view.*

class QuestionsRecyclerAdapter : RecyclerView.Adapter<QuestionHolder>() {
  public var examModel = ExamModel("", emptyList())
  val QUESTION_TYPE = 1
  val SOLVE_QUESTIONS_BTN_TYPE = 2

  var clickOnSolveExamListener: () -> Unit = {

  }
  var correctionMode: Boolean = false

  companion object {
    private val TAG = QuestionsRecyclerAdapter::javaClass.toString()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
    val layout =
        if (viewType == QUESTION_TYPE) R.layout.question_recyclerview_item_row
        else R.layout.question_solver_layout
    val inflatedView = parent.inflate(layout, false)
    return QuestionHolder(inflatedView)
  }

  override fun onBindViewHolder(holder: QuestionHolder, pos: Int) {
    if (pos < examModel.questions.size) {
      holder.itemView.questionRadioGroup.setOnCheckedChangeListener { _, _ ->}
      clearQuestions(holder)
      holder.itemView.questionRadioGroup.setOnCheckedChangeListener { _, btnId ->
        setQuestionSelected(pos, btnId)
      }
      examModel.questions[pos].let { question ->
        holder.itemView.testQuestion.text = question.questionTxt
        holder.itemView.aBtn.text = question.a
        holder.itemView.bBtn.text = question.b
        holder.itemView.cBtn.text = question.c
        holder.itemView.dBtn.text = question.d
        holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,
            if (pos % 2 == 0) R.color.lightGrey else R.color.darkGrey))
      }
      when (examModel.questions[pos].selectedAnswer) {
        0 -> {
          val isCorrect = examModel.questions[pos].correctAnswer.toLowerCase() == "a"
          if (correctionMode) {
            holder.itemView.aBtn.setBackgroundColor(if (isCorrect) Color.GREEN else Color.RED)
          }
          holder.itemView.questionRadioGroup.check(R.id.aBtn)
        }
        1 -> {
          val isCorrect = examModel.questions[pos].correctAnswer.toLowerCase() == "b"
          if (correctionMode) {
            holder.itemView.bBtn.setBackgroundColor(if (isCorrect) Color.GREEN else Color.RED)
          }
          holder.itemView.questionRadioGroup.check(R.id.bBtn)
        }
        2 -> {
          val isCorrect = examModel.questions[pos].correctAnswer.toLowerCase() == "c"
          if (correctionMode) {
            holder.itemView.cBtn.setBackgroundColor(if (isCorrect) Color.GREEN else Color.RED)
          }
          holder.itemView.questionRadioGroup.check(R.id.cBtn)
        }
        3 -> {
          val isCorrect = examModel.questions[pos].correctAnswer.toLowerCase() == "d"
          if (correctionMode) {
            holder.itemView.dBtn.setBackgroundColor(if (isCorrect) Color.GREEN else Color.RED)
          }
          holder.itemView.questionRadioGroup.check(R.id.dBtn)
        }
        else -> {
        }
      }
    } else {
      holder.itemView.solveExamBtn.setOnClickListener {
        clickOnSolveExamListener()
      }
    }
  }

  private fun clearQuestions(holder: QuestionHolder) {
    holder.itemView.aBtn.setBackgroundColor(Color.TRANSPARENT)
    holder.itemView.bBtn.setBackgroundColor(Color.TRANSPARENT)
    holder.itemView.cBtn.setBackgroundColor(Color.TRANSPARENT)
    holder.itemView.dBtn.setBackgroundColor(Color.TRANSPARENT)
    holder.itemView.questionRadioGroup.check(-1)
  }

  private fun setQuestionSelected(questionNum: Int, radioBtnId: Int) {
    if (radioBtnId > -1)
      examModel.questions[questionNum].selectedAnswer = getRadioBtnPos(radioBtnId)
  }

  private fun getRadioBtnPos(radioBtnId: Int) =
      when (radioBtnId) {
        R.id.aBtn -> 0
        R.id.bBtn -> 1
        R.id.cBtn -> 2
        R.id.dBtn -> 3
        else -> -1
      }

  override fun getItemCount(): Int = examModel.questions.size + 1

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getItemViewType(position: Int): Int {
    return if (position < examModel.questions.size)
      QUESTION_TYPE
    else
      SOLVE_QUESTIONS_BTN_TYPE

  }

  inner class QuestionHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

    override fun onClick(v: View?) {
      Log.d(TAG, "Onclick question")
    }
  }
}
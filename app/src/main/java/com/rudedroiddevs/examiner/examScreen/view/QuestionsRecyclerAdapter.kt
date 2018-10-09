package com.rudedroiddevs.examiner.examScreen.view

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rudedroiddevs.examiner.R
import com.rudedroiddevs.examiner.examScreen.view.QuestionsRecyclerAdapter.QuestionHolder
import com.rudedroiddevs.examiner.pojomodel.Exam
import com.rudedroiddevs.examiner.utils.inflate
import kotlinx.android.synthetic.main.question_recyclerview_item_row.view.*

class QuestionsRecyclerAdapter(private val exam: Exam?) :
    RecyclerView.Adapter<QuestionHolder>() {

  companion object {
    private val TAG = QuestionsRecyclerAdapter::javaClass.toString()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
    val inflatedView = parent.inflate(
        R.layout.question_recyclerview_item_row, false)
    return QuestionHolder(inflatedView)
  }

  override fun onBindViewHolder(holder: QuestionHolder, pos: Int) {
    when (exam?.questionsList?.get(pos)?.selectedAnswer) {
      0 -> holder.itemView.questionRadioGroup.check(R.id.aBtn)
      1 -> holder.itemView.questionRadioGroup.check(R.id.bBtn)
      2 -> holder.itemView.questionRadioGroup.check(R.id.cBtn)
      3 -> holder.itemView.questionRadioGroup.check(R.id.dBtn)
      else -> holder.itemView.questionRadioGroup.check(-1)
    }
    exam?.questionsList?.get(pos)?.let { question ->
      holder.itemView.testQuestion.text = question.questionTxt
      holder.itemView.aBtn.text = question.a
      holder.itemView.bBtn.text = question.b
      holder.itemView.cBtn.text = question.c
      holder.itemView.dBtn.text = question.d
      holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,
          if (pos % 2 == 0) R.color.lightGrey else R.color.darkGrey
      ))
      holder.itemView.questionRadioGroup.setOnCheckedChangeListener { _, btnId ->
        setQuestionSelected(pos, btnId)
      }
    }
  }

  private fun setQuestionSelected(questionNum: Int, radioBtnId: Int) {
    if (radioBtnId > -1)
      exam?.questionsList?.get(questionNum)?.selectedAnswer = getRadioBtnPos(radioBtnId)
  }

  private fun getRadioBtnPos(radioBtnId: Int) =
      when (radioBtnId) {
        R.id.aBtn -> 0
        R.id.bBtn -> 1
        R.id.cBtn -> 2
        R.id.dBtn -> 3
        else -> -1
      }


  override fun getItemCount(): Int = exam?.questionsList?.size ?: 0

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  inner class QuestionHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

    override fun onClick(v: View?) {
      Log.d(TAG, "Onclick question")
    }
  }
}
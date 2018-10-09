package com.rudedroiddevs.examiner.examsListScreen.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rudedroiddevs.examiner.R
import com.rudedroiddevs.examiner.utils.inflate
import kotlinx.android.synthetic.main.exam_recyclerview_item_row.view.*

class ExamsRecyclerAdapter(var examTitles: List<String>) :
    RecyclerView.Adapter<ExamsRecyclerAdapter.ExamHolder>() {

  var clickOnExamlistener: (pos: Int) -> Unit = {

  }

  companion object {
    private val TAG = ExamsRecyclerAdapter::javaClass.toString()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamHolder {
    val inflatedView = parent.inflate(
        R.layout.exam_recyclerview_item_row, false)
    return ExamHolder(inflatedView)
  }

  override fun onBindViewHolder(holder: ExamHolder, pos: Int) {
    holder.itemView.examTitle.text = examTitles[pos]
  }

  override fun getItemCount() = examTitles.size

  inner class ExamHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

    init {
      v.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
      clickOnExamlistener(adapterPosition)
    }
  }
}
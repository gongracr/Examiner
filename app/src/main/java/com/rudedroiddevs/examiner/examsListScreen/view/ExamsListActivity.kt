package com.rudedroiddevs.examiner.examsListScreen.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.rudedroiddevs.examiner.R
import com.rudedroiddevs.examiner.base.view.BaseActivity
import com.rudedroiddevs.examiner.examScreen.view.ExamActivity
import com.rudedroiddevs.examiner.examsListScreen.dagger.DaggerExamsListScreenComponent
import com.rudedroiddevs.examiner.examsListScreen.dagger.ExamsListScreenModule
import com.rudedroiddevs.examiner.examsListScreen.model.ExamsListModel
import com.rudedroiddevs.examiner.examsListScreen.presenter.ExamsListPresenter
import com.rudedroiddevs.examiner.utils.NUM_EXAM
import com.rudedroiddevs.examiner.utils.SharedPrefsUtils
import com.rudedroiddevs.examiner.utils.gotoActivity
import kotlinx.android.synthetic.main.activity_exams_list.*
import kotlinx.android.synthetic.main.activity_exams_list.view.*
import kotlinx.android.synthetic.main.fragment_exams_list.view.*
import javax.inject.Inject

class ExamsListActivity : BaseActivity(), ExamsListScreenView {
  @Inject
  lateinit var examsPresenter: ExamsListPresenter
  lateinit var examsAdapter: ExamsRecyclerAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_exams_list)
    injectDependencies()

    val linearLayoutManager = LinearLayoutManager(this)
    examsAdapter = ExamsRecyclerAdapter(emptyList())
    examsAdapter.clickOnExamlistener = { pos ->
      examsPresenter.onExamClicked(pos)
    }
    recyclerView.adapter = examsAdapter
    recyclerView.layoutManager = linearLayoutManager
    examsPresenter.viewCreated(this)
  }

  override fun onDestroy() {
    super.onDestroy()
    examsPresenter.viewDestroyed()
  }

  override fun onResume() {
    super.onResume()
    examsPresenter.viewResumed(this)
  }


  private fun injectDependencies() {
    DaggerExamsListScreenComponent.builder()
        .applicationComponent(applicationComponent)
        .examsListScreenModule(ExamsListScreenModule(this))
        .build()
        .inject(this)
  }

  override fun displayExamListModel(examsListModel: ExamsListModel) {
    examsAdapter.examTitles = examsListModel.examsTitles
    examsAdapter.notifyDataSetChanged()
  }

  override fun goToExamActivity(pos: Int) {
    gotoActivity(ExamActivity::class,
        extras = mapOf(NUM_EXAM to pos))
  }
}
package com.rudedroiddevs.examiner.examsListScreen.view

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
import com.rudedroiddevs.examiner.utils.gotoActivity
import kotlinx.android.synthetic.main.activity_exams_list.*
import kotlinx.android.synthetic.main.fragment_exams_list.view.*
import javax.inject.Inject

class ExamsListActivity : BaseActivity(), ExamsListScreenView {

  @Inject
  lateinit var examsPresenter: ExamsListPresenter
  lateinit var mainFragment: PlaceholderFragment
  private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_exams_list)
    injectDependencies()

    mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
    container.adapter = mSectionsPagerAdapter
//
//    container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
//    tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
//
//    fab.setOnClickListener { view ->
//      Snackbar.make(view, "La respuesta correcta es ", Snackbar.LENGTH_LONG)
//          .setAction("Action", null).show()
//    }
  }

  override fun onDestroy() {
    super.onDestroy()
    examsPresenter.viewDestroyed()
  }

  inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): PlaceholderFragment {
      mainFragment = PlaceholderFragment.newInstance(position + 1)
      mainFragment.presenter = examsPresenter
      return mainFragment
    }

    override fun getCount(): Int {
      return 1
    }
  }

  class PlaceholderFragment : Fragment() {
    lateinit var presenter: ExamsListPresenter

    companion object {
      private const val ARG_SECTION_NUMBER = "section_number"

      fun newInstance(sectionNumber: Int): PlaceholderFragment {
        val fragment = PlaceholderFragment()
        val args = Bundle()
        args.putInt(ARG_SECTION_NUMBER, sectionNumber)
        fragment.arguments = args
        return fragment
      }
    }

    var fragmentPos = -1

    lateinit var examsAdapter: ExamsRecyclerAdapter

    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
      val rootView = inflater.inflate(
          R.layout.fragment_exams_list, container, false)
      linearLayoutManager = LinearLayoutManager(activity)

      fragmentPos = arguments?.getInt(ARG_SECTION_NUMBER)!!
      examsAdapter = ExamsRecyclerAdapter(emptyList())
      examsAdapter.clickOnExamlistener = { pos ->
        activity?.gotoActivity(ExamActivity::class,
            extras = mapOf(NUM_EXAM to pos))
      }
      rootView?.recyclerView?.adapter = examsAdapter
      rootView?.recyclerView?.layoutManager = linearLayoutManager
      presenter.viewCreated()
      return rootView
    }
  }

  private fun injectDependencies() {
    DaggerExamsListScreenComponent.builder()
        .applicationComponent(applicationComponent)
        .examsListScreenModule(ExamsListScreenModule(this))
        .build()
        .inject(this)
  }

  override fun displayExamListModel(examsListModel: ExamsListModel) {
    mainFragment.examsAdapter.examTitles = examsListModel.examsTitles
    mainFragment.examsAdapter.notifyDataSetChanged()
  }
}
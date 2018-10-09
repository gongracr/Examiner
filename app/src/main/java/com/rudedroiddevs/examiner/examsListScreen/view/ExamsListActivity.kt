package com.rudedroiddevs.examiner.examsListScreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.rudedroiddevs.examiner.ExamActivity
import com.rudedroiddevs.examiner.R
import com.rudedroiddevs.examiner.base.view.BaseActivity
import com.rudedroiddevs.examiner.examScreen.presenter.ExamPresenter
import com.rudedroiddevs.examiner.examScreen.presenter.ExamPresenterImpl
import com.rudedroiddevs.examiner.examsListScreen.dagger.ExamsListScreenModule
import com.rudedroiddevs.examiner.examsListScreen.model.ExamsListModel
import com.rudedroiddevs.examiner.utils.NUM_EXAM
import com.rudedroiddevs.examiner.utils.gotoActivity
import kotlinx.android.synthetic.main.activity_exams_list.*
import kotlinx.android.synthetic.main.fragment_exams_list.view.*
import javax.inject.Inject

class ExamsListActivity : BaseActivity(), ExamsListScreenView {

  /**
   * The [android.support.v4.view.PagerAdapter] that will provide
   * fragments for each of the sections. We use a
   * {@link FragmentPagerAdapter} derivative, which will keep every
   * loaded fragment in memory. If this becomes too memory intensive, it
   * may be best to switch to a
   * [android.support.v4.app.FragmentStatePagerAdapter].
   */
  private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

  @Inject
  lateinit var presenter: ExamPresenterImpl

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_exams_list)
    injectDependencies()
    presenter.viewCreated()
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
    presenter.viewDestroyed()
  }

  /**
   * A [FragmentPagerAdapter] that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */
  inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(
      fm) {

    override fun getItem(position: Int): PlaceholderFragment {
      // getItem is called to instantiate the fragment for the given page.
      // Return a PlaceholderFragment (defined as a static inner class below).
      return PlaceholderFragment.newInstance(
          position + 1)
    }

    override fun getCount(): Int {
      // Show 3 total pages.
      return 1
    }
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  class PlaceholderFragment : Fragment() {
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
      view?.recyclerView?.adapter = examsAdapter
      view?.recyclerView?.layoutManager = linearLayoutManager
      return rootView
    }

    companion object {
      /**
       * The fragment argument representing the section number for this
       * fragment.
       */
      private val ARG_SECTION_NUMBER = "section_number"

      /**
       * Returns a new instance of this fragment for the given section
       * number.
       */
      fun newInstance(sectionNumber: Int): PlaceholderFragment {
        val fragment = PlaceholderFragment()
        val args = Bundle()
        args.putInt(
            ARG_SECTION_NUMBER, sectionNumber)
        fragment.arguments = args
        return fragment
      }
    }
  }

  private fun injectDependencies() {
    DaggerMainScreenComponent.builder()
        .applicationComponent(applicationComponent)
        .mainScreenModule(ExamsListScreenModule(this))
        .build()
        .inject(this)
  }

  override fun displayExamListModel(examsListModel: ExamsListModel) {
    mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

    // Set up the ViewPager with the sections adapter.
    container.adapter = mSectionsPagerAdapter
    val placeholderFragment = mSectionsPagerAdapter?.getItem(0)
    placeholderFragment?.examsAdapter?.examTitles = examsListModel.examsTitles
    placeholderFragment?.examsAdapter?.notifyDataSetChanged()
  }
}
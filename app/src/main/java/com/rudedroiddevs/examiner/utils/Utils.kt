package com.rudedroiddevs.examiner.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.rudedroiddevs.examiner.pojomodel.Exam
import kotlin.reflect.KClass

const val NUM_EXAM = "exam_num"

fun loadExams(context: Context): List<Exam> {
  val examsList = arrayListOf<Exam>()
  examsList.add(XMLParser.parseExamen(context, "Exam1.json"))
  examsList.add(XMLParser.parseExamen(context, "Exam2.json"))
  examsList.add(XMLParser.parseExamen(context, "Exam3.json"))
  examsList.add(XMLParser.parseExamen(context, "Exam4.json"))
  examsList.add(XMLParser.parseExamen(context, "Exam5.json"))
  examsList.add(XMLParser.parseExamen(context, "Exam6.json"))
  examsList.add(XMLParser.parseExamen(context, "Exam7.json"))
  examsList.add(XMLParser.parseExamen(context, "Exam8.json"))
  examsList.add(XMLParser.parseExamen(context, "Exam9.json"))
  examsList.add(XMLParser.parseExamen(context, "Exam10.json"))
  examsList.add(XMLParser.parseExamen(context, "Exam11.json"))
  return examsList
}

fun loadExam(context: Context, pos: Int): Exam? {
  return loadExams(context)[pos]
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun Activity.gotoActivity(cls: KClass<out Activity>, finish: Boolean = false,
    extras: Map<String, Any?>? = null) {
  val intent = Intent(this, cls.java)
  extras?.forEach { intent.addExtra(it.key, it.value) }
  startActivity(intent)
  if (finish) finish()
}

fun Intent.addExtra(key: String, value: Any?) {
  when (value) {
    is Long -> putExtra(key, value)
    is String -> putExtra(key, value)
    is Boolean -> putExtra(key, value)
    is Float -> putExtra(key, value)
    is Double -> putExtra(key, value)
    is Int -> putExtra(key, value)
    is Parcelable -> putExtra(key, value)
  }
}

inline fun <reified T> Activity.getExtra(extra: String): T? {
  return intent.extras?.get(extra) as? T?
}


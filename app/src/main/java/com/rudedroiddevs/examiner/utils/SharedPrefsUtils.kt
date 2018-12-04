package com.rudedroiddevs.examiner.utils

import android.content.Context
import com.google.gson.GsonBuilder
import com.rudedroiddevs.examiner.pojomodel.Exam

public class SharedPrefsUtils {
  companion object {

    val SHARED_PREFS = "shared_prefs"
    val PENDING_EXAM = "pending_exam"

    fun loadPendingExam(context: Context): Exam? {
      val sharedPrefs = context.getSharedPreferences(SHARED_PREFS, 0)
      val pendingExamJSON = sharedPrefs.getString(PENDING_EXAM, null)
      return GsonBuilder().create().fromJson(pendingExamJSON, Exam::class.java)
    }

    fun savePendingExam(context: Context, pendingExam: Exam) {
      val examJson = GsonBuilder().create().toJson(pendingExam, Exam::class.java)
      context.getSharedPreferences(SHARED_PREFS, 0).edit().putString(PENDING_EXAM,
          examJson).apply()
    }

    fun removePendingExam(context: Context) {
      context.getSharedPreferences(SHARED_PREFS, 0).edit().remove(PENDING_EXAM).apply()
    }
  }
}
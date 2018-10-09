package com.rudedroiddevs.examiner.utils

import android.content.Context
import com.google.gson.GsonBuilder
import com.rudedroiddevs.examiner.pojomodel.Exam

class XMLParser {
  companion object {
    fun parseExamen(context: Context, fileName: String): Exam {
      val jsonString = context.assets.open(fileName).bufferedReader().use {
        it.readText()
      }
      val exam: Exam = GsonBuilder().create().fromJson(jsonString, Exam::class.java)
      return exam
    }
  }
}
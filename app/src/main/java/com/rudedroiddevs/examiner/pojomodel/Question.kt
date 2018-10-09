package com.rudedroiddevs.examiner.pojomodel

data class Question(
    val questionNum: Int?,
    val questionTxt: String,
    val a: String?,
    val b: String?,
    val c: String?,
    val d: String?,
    var selectedAnswer: Int? = null,
    val correctAnswer: String
)

package com.rudedroiddevs.examiner.base.mapper

interface BaseMapper<in D, out V> {

  fun mapDataModelToViewModel(dataModel: D): V
}

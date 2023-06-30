package com.company.payroll.model

import com.github.pagehelper.PageInfo

class PageWrapper<T> {

  private var totalPage: Int = 0

  private var list: List<T>? = null

  fun <T> wrapPage(list: List<T>): PageWrapper<T> {
    val result: PageWrapper<T> = PageWrapper<T>()
    val pageInfo = PageInfo(list)
    result.totalPage = pageInfo.total.toInt()
    result.list = pageInfo.list
    return result
  }

  fun getList(): List<T>? {
    return this.list
  }

  fun setList(list: List<T>) {
    this.list = list
  }

  fun getTotalPage(): Int {
    return this.totalPage
  }

  fun setTotalPage(totalPage: Int) {
    this.totalPage = totalPage
  }
}
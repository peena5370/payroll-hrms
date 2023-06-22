package com.company.payroll.util

class Generator {
  /**
   * Method of generating random string with specific length
   * @param
   * @return  stringbuilder
   */
  fun generateRandomString(len: Int): String {
    val randomChar = "ABCDFEGHIJKLMNOPQRSTUVWXYZ1234567890"
    // for servlet use
    // StringBuffer sb = new StringBuffer(7);
    val sb = StringBuilder(len)
    for (i in 0 until len) {
      val index = (randomChar.length * Math.random()).toInt()
      sb.append(randomChar[index])
    }
    return sb.toString()
  }
}
package com.company.payroll.annotation

/**
 * @author caroline
 * <p> Created 27 June 2023
 * <p> Used for creating no-arg constructor for model class. As Kotlin data class doesn't have initial value for creating empty arg constructor like Java due to null-safe checking.<br><br>
 * <p> For Kotlin data class for creating a no-arg constructor, it can be done as below method:<br><br>
 * <p> <b>I. giving the variable an initial values</b>
 * <p> data class DemoClass:(var var1=0, var var2="") or<br><br>
 * <p> <b>II. create a constructor for data class</b>
 * <p> data class DemoClass:(var var1, var var2="") {<br>constructor(var var1, var var2): this(var1, "")<br>}<br><br>
 * <p> This annotation will create a no-arg constructor for the classes as similar as Java typed no-args constructor
 */
annotation class NoArg {
}
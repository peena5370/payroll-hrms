package com.company.payroll.util

import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.type.MappedJdbcTypes
import org.apache.ibatis.type.MappedTypes
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * @author caroline
 * <p> Created 29 June 2023
 * <p> Used for MyBatis for handle UByte values for Kotlin project. Mapped kotlin.UByte with MyBatis JdbcType.TINYINT
 */
@MappedTypes(UByte::class)
@MappedJdbcTypes(value= [JdbcType.TINYINT])
class UByteTypeHandler : BaseTypeHandler<UByte>() {
  override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: UByte, jdbcType: JdbcType?) {
    ps.setInt(i, parameter.toInt())
  }

  override fun getNullableResult(rs: ResultSet, columnName: String): UByte? {
    val value = rs.getByte(columnName)
    return if (rs.wasNull()) null else value.toUByte()
  }

  override fun getNullableResult(rs: ResultSet, columnIndex: Int): UByte? {
    val value = rs.getByte(columnIndex)
    return if (rs.wasNull()) null else value.toUByte()
  }

  override fun getNullableResult(cs: CallableStatement, columnIndex: Int): UByte? {
    val value = cs.getByte(columnIndex)
    return if (cs.wasNull()) null else value.toUByte()
  }
}

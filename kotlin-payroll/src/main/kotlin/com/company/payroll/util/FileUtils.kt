package com.company.payroll.util

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 *
 * <p> to de checked after converting from java to kotlin
 */
@Component
class FileUtils {
  private val log = KotlinLogging.logger {}

  private var storageLocation: Path? = null

  fun FileUtils(@Value("\${file.upload.directory}") directory: String) {
    storageLocation = Paths.get(directory).toAbsolutePath().normalize()
    try {
      this.storageLocation?.let { Files.createDirectories(it) }
    } catch (e: Exception) {
      log.warn("Failed to create file directory. Error message: {}", e)
    }
  }

  /**
   * Image upload utility. Accepted image file format in (filename).(jpg/jpeg/png/gif)
   * Modified at 30 Apr 2023
   *
   *  Change from upload(MultipartFile file, String path, int id) to imageUpload(MultipartFile file, String impPath)
   *
   * @param file
   * @param imgPath
   * @return String upload path
   */
  fun imageUpload(file: MultipartFile, imgPath: String): String? {
    var uploadPath: String? = null
    val filename: String? = file.originalFilename?.let { StringUtils.cleanPath(it) }
    val regex: Matcher? = filename?.let { Pattern.compile("(\\w*)(\\.)(jpg|jpeg|png|gif)", Pattern.CASE_INSENSITIVE).matcher(it) }
    if (regex != null) {
      if (!regex.matches()) {
        return uploadPath
      } else {
        val randomName = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."))
        try {
          val storagePath: Path = Path.of(java.lang.String.valueOf(storageLocation), imgPath)
          Files.createDirectories(storagePath)
          val dest: Path = storagePath.resolve(randomName)
          Files.copy(file.inputStream, dest, StandardCopyOption.REPLACE_EXISTING)
          uploadPath = dest.toString()
        } catch (e: IOException) {
          log.warn("Could not store file to directory. Error message: {}", e)
        }
      }
    }
    return uploadPath
  }

  /**
   * File upload utility, accepted file format in: doc, docs, and pdf.
   * @param file
   * @param path
   * @return String upload path
   */
  fun fileUpload(file: MultipartFile, path: String?): String? {
    var uploadPath: String? = null
    val filename: String? = file.originalFilename?.let { StringUtils.cleanPath(it) }
    val regex: Matcher? = filename?.let { Pattern.compile("(\\w*\\p{P}.*)|([0-9])|(\\w*)(\\.)(doc|docx|pdf)", Pattern.CASE_INSENSITIVE).matcher(it) }
    if (regex != null) {
      if (!regex.matches()) {
        return uploadPath
      } else {
        try {
          val storagePath: Path = Path.of(java.lang.String.valueOf(storageLocation), path)
          Files.createDirectories(storagePath)
          val dest: Path? = filename?.let { storagePath.resolve(it) }
          if (dest != null) {
            Files.copy(file.inputStream, dest, StandardCopyOption.REPLACE_EXISTING)
          }
          uploadPath = dest.toString()
        } catch (e: IOException) {
          log.warn("Could not store file to directory. Error message: {}", e)
        }
      }
    }
    return uploadPath
  }

  /**
   *
   * Modified at 30 Apr 2022
   *
   *  Change from download(String filename, Path path) to download(Path path)
   *
   * @param imgPath
   * @return Resource
   */
  fun download(path: Path): Resource? {
    var resource: Resource? = null
    try {
      val urlResource: Resource = UrlResource(path.toUri())
      if (urlResource.exists()) {
        resource = urlResource
      }
    } catch (e: MalformedURLException) {
      log.info("File not found in directory. Error message: {}", e)
    }
    return resource
  }

  /**
   * File delete utility
   * @param path
   * @return boolean
   */
  fun delete(path: Path?): Boolean {
    var bool = false
    try {
      bool = path?.let { Files.deleteIfExists(it) } == true
    } catch (e: IOException) {
      log.info("Delete file fail. Error message: {}", e)
    }
    return bool
  }
}
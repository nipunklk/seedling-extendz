package com.hrandika.seedling.spring.core.services.minio

import com.hrandika.seedling.spring.utils.logger
import io.minio.GetObjectArgs
import io.minio.MinioClient
import io.minio.UploadObjectArgs
import org.apache.commons.io.IOUtils
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


@Service
class MinioService(val minioClient: MinioClient) {
    val log: Logger = logger<MinioService>()!!

    @Value("\${minio.bucket.name}")
    val bucketName: String? = null

    @Throws(IOException::class)
    fun convertMultiPartToFile(file: MultipartFile): File {
        val temp = File.createTempFile(file.originalFilename, ".tmp")
        val fos = FileOutputStream(temp)
        fos.write(file.bytes)
        fos.close()
        log.info("Temporary file created {}", temp.absolutePath)
        return temp
    }

    fun uploadImage(file: MultipartFile, id: String, dir: String): String {
        var imageFile = convertMultiPartToFile(file)
        val uploadObjectArgs =
            UploadObjectArgs.builder().bucket(this.bucketName)
                .`object`("${dir}/${id}")
                .filename(imageFile.absolutePath)
                .contentType("image")
                .build()
        this.minioClient.uploadObject(uploadObjectArgs)
        return file.originalFilename!!
    }

    fun getFile(id: String?, dir: String): ByteArray? {
        try {
            val obj = minioClient.getObject(
                GetObjectArgs.builder().bucket(this.bucketName)
                    .`object`("${dir}/${id}")
                    .build()
            )
            val content: ByteArray = IOUtils.toByteArray(obj)
            obj.close()
            return content
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
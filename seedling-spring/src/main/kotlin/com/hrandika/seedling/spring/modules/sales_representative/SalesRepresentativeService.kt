package com.hrandika.seedling.spring.modules.sales_representative

import com.hrandika.seedling.spring.core.services.minio.MinioDirectories
import com.hrandika.seedling.spring.core.services.minio.MinioService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class SalesRepresentativeService(
    var salesRepresentativeRepository: SalesRepresentativeRepository,
    var minioService: MinioService
) {

    fun getFullName(salesRepresentative: SalesRepresentative?): String {
        if (salesRepresentative != null) return "${salesRepresentative.firstName} ${salesRepresentative.lastName}"
        return ""
    }

    fun uploadFrontImages(file: MultipartFile, id: String, baseUrl: String): Optional<SalesRepresentative> {
        val optional = this.salesRepresentativeRepository.findById(id)
        if (optional.isPresent) {
            this.minioService.uploadImage(file, id, MinioDirectories.nicFrontImages)
            val entity = optional.get()
            entity.nicFront = baseUrl
            this.salesRepresentativeRepository.save(entity)
            return Optional.of(entity);
        }
        return Optional.empty()
    }

    fun getFrontFile(id: String): ByteArray? {
        val optional = this.salesRepresentativeRepository.findById(id)
        if (optional.isPresent) return this.minioService.getFile(optional.get().id, MinioDirectories.nicFrontImages)
        return null
    }

    fun uploadBackImages(file: MultipartFile, id: String, baseUrl: String): Optional<SalesRepresentative> {
        val optional = this.salesRepresentativeRepository.findById(id)
        if (optional.isPresent) {
            this.minioService.uploadImage(file, id, MinioDirectories.nicBackImages)
            val entity = optional.get()
            entity.nicBack = baseUrl
            this.salesRepresentativeRepository.save(entity)
            return Optional.of(entity);
        }
        return Optional.empty()
    }

    fun getBackFile(id: String): ByteArray? {
        val optional = this.salesRepresentativeRepository.findById(id)
        if (optional.isPresent) return this.minioService.getFile(optional.get().id, MinioDirectories.nicBackImages)
        return null
    }
}
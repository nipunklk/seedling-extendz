package com.hrandika.seedling.spring.modules.product

import com.hrandika.seedling.spring.core.services.minio.MinioDirectories
import com.hrandika.seedling.spring.core.services.minio.MinioService
import com.hrandika.seedling.spring.utils.logger
import org.slf4j.Logger
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class ProductService(
    var productRepository: ProductRepository,
    var miniService: MinioService
) {
    val log: Logger = logger<ProductService>()!!

    fun uploadImages(file: MultipartFile, id: String, baseUrl: String): Optional<Product> {
        val optional = this.productRepository.findById(id)
        if (optional.isPresent) {
            this.miniService.uploadImage(file, id, MinioDirectories.productImages)
            val entity = optional.get()
            entity.image = baseUrl
            this.productRepository.save(entity)
            return Optional.of(entity);
        }
        return Optional.empty()
    }

    fun getFile(id: String): ByteArray? {
        val optional = this.productRepository.findById(id)
        if (optional.isPresent) return this.miniService.getFile(optional.get().id, MinioDirectories.productImages)
        return null
    }

}
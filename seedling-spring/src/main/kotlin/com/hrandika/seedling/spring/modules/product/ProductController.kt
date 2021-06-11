package com.hrandika.seedling.spring.modules.product

import org.springframework.core.io.ByteArrayResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.security.Principal
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = ["\${spring.data.rest.base-path}/products"])
class ProductController(val productService: ProductService) {

    @PostMapping(value = ["/{id}/image"])
    fun setProductImage(
        @PathVariable(value = "id", required = true) id: String,
        @RequestParam(value = "image", required = true) file: MultipartFile,
        principal: Principal?,
        request: HttpServletRequest?
    ): ResponseEntity<*>? {
        val p: Optional<Product> = productService.uploadImages(file, id, request?.requestURL.toString())
        return ResponseEntity.of<Product>(p)
    }

    @GetMapping(value = ["/{id}/image"])
    fun uploadFile(@PathVariable(value = "id", required = true) id: String): ResponseEntity<ByteArrayResource?>? {
        val data: ByteArray? = productService.getFile(id)
        val resource = data?.let { ByteArrayResource(it) }
        if (data != null) {
            return ResponseEntity
                .ok()
                .contentLength(data.size.toLong())
                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"$file\"")
                .body(resource)
        }
        return null
    }
}
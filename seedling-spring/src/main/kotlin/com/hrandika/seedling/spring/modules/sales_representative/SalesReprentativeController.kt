package com.hrandika.seedling.spring.modules.sales_representative

import org.springframework.core.io.ByteArrayResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.security.Principal
import java.util.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping(value = ["\${spring.data.rest.base-path}/salesRepresentatives"])
class SalesRepresentativeController(val salesRepresentativeService: SalesRepresentativeService) {

    @PostMapping(value = ["/{id}/nicFront"])
    fun setNicFrontImage(
        @PathVariable(value = "id", required = true) id: String,
        @RequestParam(value = "nicFront", required = true) file: MultipartFile,
        principal: Principal?,
        request: HttpServletRequest?
    ): ResponseEntity<*>? {
        val p: Optional<SalesRepresentative> = salesRepresentativeService.uploadFrontImages(file, id, request?.requestURL.toString())
        return ResponseEntity.of<SalesRepresentative>(p)
    }

    @GetMapping(value = ["/{id}/nicFront"])
    fun getFrontImage(@PathVariable(value = "id", required = true) id: String): ResponseEntity<ByteArrayResource?>? {
        val data: ByteArray? = salesRepresentativeService.getFrontFile(id)
        val resource = data?.let { ByteArrayResource(it) }
        if (data != null) {
            return ResponseEntity
                .ok()
                .contentLength(data.size.toLong())
                .header("Content-type", "application/octet-stream")
                .body(resource)
        }
        return null
    }

    @PostMapping(value = ["/{id}/nicBack"])
    fun setNicBackImage(
        @PathVariable(value = "id", required = true) id: String,
        @RequestParam(value = "nicBack", required = true) file: MultipartFile,
        principal: Principal?,
        request: HttpServletRequest?
    ): ResponseEntity<*>? {
        val p: Optional<SalesRepresentative> = salesRepresentativeService.uploadBackImages(file, id, request?.requestURL.toString())
        return ResponseEntity.of<SalesRepresentative>(p)
    }



    @GetMapping(value = ["/{id}/nicBack"])
    fun getBackImage(@PathVariable(value = "id", required = true) id: String): ResponseEntity<ByteArrayResource?>? {
        val data: ByteArray? = salesRepresentativeService.getBackFile(id)
        val resource = data?.let { ByteArrayResource(it) }
        if (data != null) {
            return ResponseEntity
                .ok()
                .contentLength(data.size.toLong())
                .header("Content-type", "application/octet-stream")
                .body(resource)
        }
        return null
    }
}
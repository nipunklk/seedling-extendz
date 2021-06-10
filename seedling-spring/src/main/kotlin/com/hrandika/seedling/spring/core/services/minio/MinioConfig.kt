package com.hrandika.seedling.spring.core.services.minio

import io.minio.BucketExistsArgs
import io.minio.MakeBucketArgs
import io.minio.MinioClient
import io.minio.SetBucketPolicyArgs
import net.minidev.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
public class MinioConfig {
    @Value("\${minio.access.name}")
    val accessKey: String? = null

    @Value("\${minio.access.secret}")
    val accessSecret: String? = null

    @Value("\${minio.url}")
    val minioUrl: String? = null

    @Value("\${minio.bucket.name}")
    val bucketName: String? = null

    @Bean
    fun generateMinioClient(): MinioClient? {
        val minioClient = MinioClient.builder()
            .endpoint(minioUrl)
            .credentials(accessKey, accessSecret)
            .build()
        val found: Boolean = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())
        if (!found) {
            minioClient.makeBucket(
                MakeBucketArgs.builder().bucket(bucketName)
                    .build()
            )
            var policy = JSONObject(
                mapOf(
                    "Version" to "2012-10-17",
                    "Statement" to listOf<Any>(
                        mapOf(
                            "Effect" to "Allow",
                            "Principal" to mapOf(
                                "AWS" to listOf<String>(
                                    "*"
                                )
                            ),
                            "Action" to listOf<String>(
                                "s3:ListBucketMultipartUploads",
                                "s3:GetBucketLocation",
                                "s3:ListBucket",
                            ),
                            "Resource" to listOf<String>(
                                "arn:aws:s3:::$bucketName"
                            )
                        ),
                        mapOf(
                            "Effect" to "Allow",
                            "Principal" to mapOf(
                                "AWS" to listOf(
                                    "*"
                                )
                            ),
                            "Action" to listOf(
                                "s3:PutObject",
                                "s3:AbortMultipartUpload",
                                "s3:DeleteObject",
                                "s3:GetObject",
                                "s3:ListMultipartUploadParts",
                            ),
                            "Resource" to listOf(
                                "arn:aws:s3:::$bucketName/*"
                            )
                        ),
                    )
                )
            )
            println(policy.toJSONString())

            minioClient.setBucketPolicy(
                SetBucketPolicyArgs.builder()
                    .config(policy.toJSONString())
                    .bucket(this.bucketName)
                    .build()
            )
        } else println("Bucket $bucketName already exists.")
        return minioClient
    }

}
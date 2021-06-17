package com.example.crawler.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.redis.core.RedisHash
import java.time.LocalDateTime

@Document(collection = "proxies")
class Proxy(
    @Id val id: String,
    val host: String,
    val port: String,
    val source: String,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val timeoutDate: LocalDateTime?
) {
    override fun toString(): String {
        return "Proxy(id=$id, host='$host', port='$port', source='$source', createdDate=$createdDate, timeoutDate=$timeoutDate)"
    }
}
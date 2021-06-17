package com.example.crawler.model.cache

import com.example.crawler.model.enums.Sources
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Reference
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("url")
data class CrawledURL(@Indexed val uuid: String, val url: String, val source: Sources) {

    @get:Id
    var id: String? = null

}
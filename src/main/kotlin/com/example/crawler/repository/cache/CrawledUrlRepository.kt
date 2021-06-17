package com.example.crawler.repository.cache

import com.example.crawler.model.cache.CrawledURL
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface  CrawledUrlRepository: CrudRepository<CrawledURL, String> {
}
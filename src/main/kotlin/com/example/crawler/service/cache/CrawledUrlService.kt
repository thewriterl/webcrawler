package com.example.crawler.service.cache

import com.example.crawler.model.cache.CrawledURL
import com.example.crawler.repository.cache.CrawledUrlRepository
import org.springframework.stereotype.Service

@Service
class CrawledUrlService(val crawledUrlRepository: CrawledUrlRepository) {

    fun save(crawledURL: CrawledURL): CrawledURL {
        return this.crawledUrlRepository.save(crawledURL)
    }

    fun saveAll(crawledURLs: List<CrawledURL>): MutableIterable<CrawledURL> {
        return this.crawledUrlRepository.saveAll(crawledURLs)
    }

    fun findAll(): MutableIterable<CrawledURL> {
        return this.crawledUrlRepository.findAll()
    }
}
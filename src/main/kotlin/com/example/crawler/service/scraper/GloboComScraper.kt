package com.example.crawler.service.scraper

import com.example.crawler.service.ProxyService
import com.example.crawler.service.helpers.WebdriverHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class GloboComScraper(@Autowired val proxyService: ProxyService, @Autowired val webdriverHelper: WebdriverHelper) {

    @Scheduled(fixedDelay = 1000 * 60 * 60)
    fun teste() {
        val chromeDriver = webdriverHelper.setupHeadlessWithRandomProxy()
        chromeDriver.get("https://www.globo.com")
        print("asdf")
    }

}
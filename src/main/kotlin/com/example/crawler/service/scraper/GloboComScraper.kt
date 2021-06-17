package com.example.crawler.service.scraper

import com.example.crawler.model.cache.CrawledURL
import com.example.crawler.model.enums.Sources
import com.example.crawler.service.ProxyService
import com.example.crawler.service.cache.CrawledUrlService
import com.example.crawler.service.helpers.WebdriverHelper
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
@EnableScheduling
class GloboComScraper(@Autowired val proxyService: ProxyService, @Autowired val webdriverHelper: WebdriverHelper, @Autowired val crawledUrlService: CrawledUrlService) {

    @Scheduled(fixedDelay = 1000 * 60 * 60)
    fun teste() {
        val chromeDriver = webdriverHelper.setupHeadlessWithRandomProxy()
        chromeDriver.thenAccept(this::scrape)
        print("asdf")
    }

    private fun scrape(chromeDriver: ChromeDriver) {
        this.crawledUrlService.save(CrawledURL("123", "123", Sources.GloboCom))
        this.crawledUrlService.findAll()
        chromeDriver.get("https://www.globo.com")
        val elements = chromeDriver.findElementsByClassName("highlight__link")
        val urls = elements.map { el ->
            el.getAttribute("href")
        }
        val crawledurls = urls.map { url -> CrawledURL("123", url, Sources.GloboCom) }
        this.crawledUrlService.saveAll(crawledurls)
        print("")
    }

}
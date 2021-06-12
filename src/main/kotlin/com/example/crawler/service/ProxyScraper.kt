package com.example.crawler.service

import com.example.crawler.model.entity.Proxy
import com.example.crawler.repository.ProxyRepository
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collectors
import mu.KotlinLogging


@Component
@EnableScheduling
class ProxyScraper(@Autowired val proxyRepository: ProxyRepository) {

    private val baseUrl = "http://www.freeproxylists.net/?a[]=1&a[]=2&s=u"
    private val logger = KotlinLogging.logger {}



    @Scheduled(fixedDelay = 1000 * 60 * 60)
    fun teste() {
        proxyRepository.deleteAll()
        val driver = ChromeDriver()
        driver.get(baseUrl)
        var pages = driver.findElementsByClassName("page").first().findElements(By.tagName("a")).count() - 1
        for (i in 1..pages) {
            driver.get("$baseUrl&page=$i")
            val tables = driver.findElementsByTagName("tbody")
            val selectedTable = tables.stream().skip(tables.stream().count() - 1).findFirst().get()
            val data = selectedTable.findElements(By.tagName("tr")).stream().skip(1).collect(Collectors.toList())
            for (row in data) {
                try {
                    val host = row.findElement(By.cssSelector("td > a"))?.text
                    val port = row.findElement(By.cssSelector("td:nth-child(2)"))?.text
                    val source = baseUrl.split("?").first()
                    proxyRepository.save(
                        Proxy(
                            UUID.randomUUID().toString(),
                            host.orEmpty(),
                            port.orEmpty(),
                            source,
                            LocalDateTime.now(),
                            null
                        )
                    )
                } catch (ex: NoSuchElementException) {
                    logger.error { "elemento de scrape não encontrado -> " }
                }

            }
        }
        driver.close()
        driver.quit()
    }

}
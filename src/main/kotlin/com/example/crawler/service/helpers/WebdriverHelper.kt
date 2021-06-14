package com.example.crawler.service.helpers

import com.example.crawler.service.ProxyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.openqa.selenium.Proxy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

@Service
class WebdriverHelper(@Autowired val proxyService: ProxyService) {

    fun setupHeadlessWithRandomProxy(): ChromeDriver {
        val proxy = Proxy()
        val randomDbProxy = proxyService.getAllProxies().random()
        proxy.setHttpProxy("${randomDbProxy.host}:${randomDbProxy.port}")
        val options = ChromeOptions()
        options.setCapability("proxy", proxy)
        options.setHeadless(true)
        options.addArguments("user-agent=Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_6_1) AppleWebKit/5340 (KHTML, like Gecko) Chrome/38.0.850.0 Mobile Safari/5340")
        return ChromeDriver(options)
    }
}
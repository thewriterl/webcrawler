package com.example.crawler.service.impl

import com.example.crawler.model.entity.Proxy
import com.example.crawler.repository.ProxyRepository
import com.example.crawler.service.ProxyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProxyServiceImpl(@Autowired val proxyRepository: ProxyRepository): ProxyService {

    override fun getAllProxies(): List<Proxy> {
        return proxyRepository.findAll()
    }
}
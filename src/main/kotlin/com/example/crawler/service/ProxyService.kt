package com.example.crawler.service

import com.example.crawler.model.entity.Proxy
import com.example.crawler.repository.ProxyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

public interface ProxyService {

    fun getAllProxies(): List<Proxy>


}
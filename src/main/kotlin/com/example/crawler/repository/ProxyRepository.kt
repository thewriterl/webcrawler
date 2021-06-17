package com.example.crawler.repository

import com.example.crawler.model.entity.Proxy
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ProxyRepository : MongoRepository<Proxy, String> {

    fun findOneById(id: ObjectId): Proxy
    override fun deleteAll()

}
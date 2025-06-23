package com.example.demo.repository;

import com.example.demo.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface repo extends MongoRepository<Url, String> {
    Optional<Url> findByShortUrl(String shortUrl);
}
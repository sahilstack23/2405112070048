package com.example.demo.service;

import com.example.demo.dt.urlRequest;
import com.example.demo.dt.urlResponse;
import com.example.demo.model.Url;
import com.example.demo.repository.repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class urlService {

    @Autowired
    private repo urlRepository;

    private static final String BASE_URL = "http://localhost:8080/";

    public urlResponse createShortUrl(urlRequest request) {
        String code = generateRandomCode(6);
        Url url = new Url();
        url.setOriginalUrl(request.getOriginalUrl());
        url.setShortUrl(code);
        url.setHitCount(0);
        urlRepository.save(url);

        return new urlResponse(
                url.getOriginalUrl(),
                BASE_URL + code,
                url.getHitCount()
        );
    }

    public String getOriginalUrl(String code) {
        Url url = urlRepository.findByShortUrl(code)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));

        url.setHitCount(url.getHitCount() + 1);
        urlRepository.save(url);
        return url.getOriginalUrl();
    }

    public urlResponse getAnalytics(String shortCode) {
        Url url = urlRepository.findByShortUrl(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"));
        return new urlResponse(url.getOriginalUrl(), BASE_URL + url.getShortUrl(), url.getHitCount());
    }

    private String generateRandomCode(int length) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, length);
    }
}
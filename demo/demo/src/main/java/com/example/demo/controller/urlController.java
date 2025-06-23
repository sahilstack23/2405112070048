package com.example.demo.controller;

import com.example.demo.dt.urlRequest;
import com.example.demo.dt.urlResponse;
import com.example.demo.service.urlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class urlController {

    @Autowired
    private urlService urlServ;

    @PostMapping("/shorten")
    public ResponseEntity<urlResponse> shortenUrl(@RequestBody urlRequest request) {
        return ResponseEntity.ok(urlServ.createShortUrl(request));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String originalUrl = urlServ.getOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }

    @GetMapping("/analytics/{shortCode}")
    public ResponseEntity<urlResponse> getAnalytics(@PathVariable String shortCode) {
        return ResponseEntity.ok(urlServ.getAnalytics(shortCode));
    }
}

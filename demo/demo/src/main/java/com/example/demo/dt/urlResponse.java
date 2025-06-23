package com.example.demo.dt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class urlResponse {
    private String originalUrl;
    private String shortUrl;
    private int hitCount;
}

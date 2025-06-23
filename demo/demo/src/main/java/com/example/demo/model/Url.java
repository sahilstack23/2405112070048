package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "urls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    @Id
    private String id;
    private String originalUrl;
    private String shortUrl;
    private int hitCount;
}

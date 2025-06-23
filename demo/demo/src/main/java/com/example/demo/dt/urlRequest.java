package com.example.demo.dt;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class urlRequest {
    @NotNull
    @URL
    private String originalUrl;
}
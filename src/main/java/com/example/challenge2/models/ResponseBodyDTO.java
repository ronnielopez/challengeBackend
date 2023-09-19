package com.example.challenge2.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
@Getter
@Setter
@Data
public class ResponseBodyDTO<T> implements Serializable {
    private String status;
    private String message;
    private T data;
    private Integer code;

    // Setter and Getter
    public void setStatus(HttpStatus status) {
        this.status = status.toString();
        this.code = status.value();
    }

}

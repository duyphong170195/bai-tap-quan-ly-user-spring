package com.example.baitapquanlyuser.apierror;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ResponseError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String type;
    private String title;
    private HttpStatus status;
    private String detail;
    private String instance;

    private ResponseError() {
        timestamp = LocalDateTime.now();
    }

    public ResponseError(HttpStatus status) {
        this();
        this.status = status;
    }


}

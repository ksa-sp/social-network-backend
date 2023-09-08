package ru.skillbox.socialnet.data;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorResponse extends ApiResponse {

    private String error;
    private String error_description;

    public ErrorResponse() {
        super();
    }
}

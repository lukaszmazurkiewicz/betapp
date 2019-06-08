package com.kodilla.betapp.coupon;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class CouponCannotBeMakeException extends RuntimeException {
    public CouponCannotBeMakeException(String message) {
        super(message);
    }
}

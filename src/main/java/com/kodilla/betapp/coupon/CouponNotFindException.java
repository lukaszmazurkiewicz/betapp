package com.kodilla.betapp.coupon;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CouponNotFindException extends RuntimeException {
    public CouponNotFindException(String message) {
        super(message);
    }
}

package com.kodilla.betapp.coupon;

import java.math.BigDecimal;

public interface CouponServiceInterface {
    Coupon addCoupon();
    void checkCoupon();
    BigDecimal payoff();
}

package com.kodilla.betapp.coupon;

public interface CouponServiceInterface {
    Coupon addCoupon(Coupon coupon);
    Coupon checkCoupon(Long id);
    void payoff(Long id);
}

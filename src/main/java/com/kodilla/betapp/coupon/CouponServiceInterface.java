package com.kodilla.betapp.coupon;

public interface CouponServiceInterface {
    Coupon getCouponById(long id);
    Coupon addCoupon(Coupon coupon);
    Coupon checkCoupon(long id);
    void payoff(long id);
}

package com.kodilla.betapp.coupon;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CouponService implements CouponServiceInterface {
    private final CouponRepository couponRepository;

    public Coupon getCouponById(long id) {
        return couponRepository.findById(id).orElseThrow(() -> new CouponNotFindException("Coupon with id " + id + " not found"));
    }

    @Override
    public Coupon addCoupon() {
        return null;
    }

    @Override
    public void checkCoupon() {

    }

    @Override
    public BigDecimal payoff() {
        return null;
    }
}

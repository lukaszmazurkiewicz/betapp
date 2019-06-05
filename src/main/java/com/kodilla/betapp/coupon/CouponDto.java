package com.kodilla.betapp.coupon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CouponDto {
    private long id;
    private long userId;
    private List<Coupon> coupons;
}

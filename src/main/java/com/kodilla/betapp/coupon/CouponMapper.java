package com.kodilla.betapp.coupon;

import com.kodilla.betapp.user.User;
import com.kodilla.betapp.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class CouponMapper {
    private final UserService userService;

    Coupon mapToCoupon(final CouponDto couponDto) {
        User user = userService.getUserById(couponDto.getUserId());
        return new Coupon(
                couponDto.getId(),
                couponDto.isWinner(),
                user,
                new ArrayList<>()
        );
    }

    CouponDto mapToCouponDto(final Coupon coupon) {
        return new CouponDto(
                coupon.getId(),
                coupon.isWinner(),
                coupon.getUser().getId(),
                coupon.getEvents()
        );
    }
}

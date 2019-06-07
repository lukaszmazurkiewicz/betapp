package com.kodilla.betapp.coupon;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CouponController {
    private final CouponMapper couponMapper;
    private final CouponService couponService;

    @PostMapping
    long addCoupon(@RequestBody CouponDto couponDto) {
        log.info("Add coupon called. CouponDto [{}]", couponDto);

        Coupon coupon = couponService.addCoupon(couponMapper.mapToCoupon(couponDto));
        return coupon.getId();
    }

    @PatchMapping("/{id}")
    CouponDto checkCoupon(@PathVariable long id) {
        log.info("Checking coupon with id [{}]", id);

        return couponMapper.mapToCouponDto(couponService.checkCoupon(id));
    }

    @PatchMapping("/payoff/{id}")
    void payoff(@PathVariable long id) {
        log.info("Payoff to user with id [{}]", id);

        couponService.payoff(id);
    }
}

package com.kodilla.betapp.coupon;

import com.kodilla.betapp.user.User;
import com.kodilla.betapp.user.UserService;
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
@RequestMapping("/coupons")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CouponController {
    private final CouponMapper couponMapper;
    private final CouponService couponService;
    private final UserService userService;

    @PostMapping
    long addCoupon(@RequestBody CouponDto couponDto) {
        log.info("Add coupon called. CouponDto [{}]", couponDto);

        User user = userService.getUserById(couponDto.getUserId());
        if ( user.getWallet().getAccountBalance().compareTo(couponDto.getStake()) < 0) {
            throw new CouponCannotBeMakeException("You can't make a bet for " + couponDto.getStake()
                    + ". Insufficient funds on your account: " + user.getWallet().getAccountBalance());
        } else {
            Coupon coupon = couponService.addCoupon(couponMapper.mapToCoupon(couponDto));
            return coupon.getId();
        }
    }

    @PatchMapping("/{id}")
    void checkCoupon(@PathVariable long id) {
        log.info("Checking coupon with id [{}]", id);

        couponService.checkCoupon(id);
    }

    @PatchMapping("/payoff/{id}")
    void payoff(@PathVariable long id) {
        log.info("Payoff to user with id [{}]", id);

        couponService.payoff(id);
    }
}

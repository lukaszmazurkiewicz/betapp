package com.kodilla.betapp.coupon;

import com.kodilla.betapp.event.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CouponService implements CouponServiceInterface {
    private final CouponRepository couponRepository;

    @Override
    public Coupon getCouponById(long id) {
        return couponRepository.findById(id).orElseThrow(() -> new CouponNotFindException("Coupon with id " + id + " not found"));
    }

    @Override
    public Coupon addCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon checkCoupon(long id) {
        Coupon coupon = getCouponById(id);

        List<Event> loosingEvents = coupon.getEvents().stream()
                .filter(c -> c.isWin() == false)
                .collect(Collectors.toList());

        if (loosingEvents.size() > 0) {
            coupon.setWinner(false);
        } else {
            coupon.setWinner(true);
        }

        return couponRepository.save(coupon);

    }

    @Override
    public void payoff(long id) {
        Coupon coupon = getCouponById(id);
        BigDecimal payment = new BigDecimal(25).add(coupon.getUser().getWallet().getAccountBalance());
        if (coupon.isWinner()) {
            coupon.getUser().getWallet().setAccountBalance(payment);
        }
    }
}

package com.kodilla.betapp.coupon;

import com.kodilla.betapp.event.Event;
import com.kodilla.betapp.wallet.Wallet;
import com.kodilla.betapp.wallet.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CouponService implements CouponServiceInterface {
    private final CouponRepository couponRepository;
    private final WalletRepository walletRepository;

    @Override
    public Coupon getCouponById(long id) {
        return couponRepository.findById(id).orElseThrow(() -> new CouponNotFindException("Coupon with id " + id + " not found"));
    }

    @Override
    public Coupon addCoupon(Coupon coupon) {
        BigDecimal amountToSet = coupon.getUser().getWallet().getAccountBalance().subtract(coupon.getStake());
        coupon.getUser().getWallet().setAccountBalance(amountToSet);

        return couponRepository.save(coupon);
    }

    @Override
    public Coupon checkCoupon(long id) {
        Coupon coupon = getCouponById(id);

        List<Event> loosingEvents = coupon.getEvents().stream()
                .filter(c -> !c.isWin())
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
        List<Event> eventsToCount = coupon.getEvents();
        BigDecimal payment = new BigDecimal(0);
        for (Event event: eventsToCount) {
            BigDecimal valueToAdd = coupon.getStake().multiply(event.getBetOdds());
            payment = payment.add(valueToAdd);
        }
        if (coupon.isWinner()) {
            payment = payment.add(coupon.getUser().getWallet().getAccountBalance());
            coupon.getUser().getWallet().setAccountBalance(payment);
        }
        walletRepository.save(coupon.getUser().getWallet());
    }
}

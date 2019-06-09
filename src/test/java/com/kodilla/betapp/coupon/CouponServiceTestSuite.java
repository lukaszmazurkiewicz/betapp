package com.kodilla.betapp.coupon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CouponServiceTestSuite {
    @InjectMocks
    private CouponService couponService;

    @Mock
    private CouponRepository couponRepository;

    @Test
    public void testGetCouponById() {
        //Given
        Coupon coupon = new Coupon(BigDecimal.ONE, false);

        when(couponRepository.findById(anyLong())).thenReturn(Optional.of(coupon));

        //When
        Coupon testCoupon = couponService.getCouponById(1L);

        //Then
        assertEquals(coupon.getId(), testCoupon.getId());
        assertEquals(coupon.getStake(), testCoupon.getStake());
        assertFalse(testCoupon.isWinner());
    }

    @Test(expected = CouponNotFindException.class)
    public void testGetCouponByIdThrewException() {
        //Given
        Coupon coupon = new Coupon(BigDecimal.ONE, false);

        //When
        Coupon testCoupon = couponService.getCouponById(1L);

        //Then
    }
}
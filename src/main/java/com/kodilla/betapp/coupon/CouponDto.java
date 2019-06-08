package com.kodilla.betapp.coupon;

import com.kodilla.betapp.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponDto {
    private long id;
    private BigDecimal stake;
    private boolean winner;
    private long userId;
    private List<Event> events;
}

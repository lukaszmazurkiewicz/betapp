package com.kodilla.betapp.coupon;

import com.kodilla.betapp.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CouponDto {
    private long id;
    private boolean winner;
    private long userId;
    private List<Event> eventss;
}

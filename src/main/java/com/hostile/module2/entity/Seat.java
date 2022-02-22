package com.hostile.module2.entity;

import lombok.Data;

@Data
public class Seat {
    private Integer[] line = new Integer[26];
    private String[] place = new String[6];
    private Users passenger;
}

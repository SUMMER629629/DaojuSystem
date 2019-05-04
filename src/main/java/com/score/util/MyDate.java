package com.score.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate extends Date {

    @Override
    public String toString() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(this);
    }
}
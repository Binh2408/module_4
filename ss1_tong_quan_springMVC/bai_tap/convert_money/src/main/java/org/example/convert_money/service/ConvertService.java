package org.example.convert_money.service;

import org.springframework.stereotype.Service;

@Service
public class ConvertService {
    private final double RATE = 23000;
    public double convertUsdToVnd(double usd) {
        return usd * RATE;
    }

    public double convertVndToUsd(double vnd) {
        return vnd / RATE;
    }
}

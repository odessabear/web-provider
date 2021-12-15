package com.odebar.webprovider.services;

import com.odebar.webprovider.repository.entity.Tariff;

import java.util.List;

public interface BusinessService {
    void doSomething();

    List<Tariff> getTariffs();
}

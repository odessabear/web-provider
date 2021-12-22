package com.odebar.webprovider.services;

import com.odebar.webprovider.repository.entity.Tariff;

import java.util.List;

public interface TariffsService {

    List<Tariff> tariffsList(int page, int limit);
}

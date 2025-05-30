package com.mm.mm.service;

import com.mm.mm.entity.ExchangeInfo;

import com.mm.mm.repository.ExchangeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeInfoService {
    @Autowired
    private ExchangeInfoRepository exchangeInfoRepository;


}


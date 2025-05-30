package com.mm.mm.repository;

import com.mm.mm.entity.ExchangeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeInfoRepository extends JpaRepository<ExchangeInfo, Integer> {

}
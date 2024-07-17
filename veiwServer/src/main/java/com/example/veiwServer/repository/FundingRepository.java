package com.example.veiwServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veiwServer.entity.Funding;

public interface FundingRepository extends JpaRepository<Funding, Long> {
}
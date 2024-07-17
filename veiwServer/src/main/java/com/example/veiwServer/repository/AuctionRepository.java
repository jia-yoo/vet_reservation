package com.example.veiwServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veiwServer.entity.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
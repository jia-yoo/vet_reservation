package com.example.veiwServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veiwServer.entity.FundingParticipant;

public interface FundingParticipantRepository extends JpaRepository<FundingParticipant, Long> {
}
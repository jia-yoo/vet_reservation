package com.example.veiwServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veiwServer.entity.Community;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
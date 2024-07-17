package com.example.veiwServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veiwServer.entity.CurrentPoint;

public interface CurrentPointRepository extends JpaRepository<CurrentPoint, Long> {
}
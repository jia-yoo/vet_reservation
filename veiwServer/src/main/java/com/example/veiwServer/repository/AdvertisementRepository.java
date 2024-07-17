package com.example.veiwServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veiwServer.entity.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
	
	

}
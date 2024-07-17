package com.example.veiwServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.veiwServer.entity.Doctor;

import jakarta.transaction.Transactional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	List<Doctor> findAllByHospitalId(@Param("hospitalId")long hospitalId);
	
	 @Modifying
	    @Transactional
	    @Query("DELETE FROM Doctor d WHERE d.hospital.id = :hospitalId")
	    void deleteByHospitalId(@Param("hospitalId") Long hospitalId);
}

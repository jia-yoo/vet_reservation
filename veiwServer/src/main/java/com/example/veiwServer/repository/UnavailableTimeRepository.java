package com.example.veiwServer.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.veiwServer.entity.UnavailableTime;

public interface UnavailableTimeRepository extends JpaRepository<UnavailableTime, Long> {
	
	List<UnavailableTime> findAllByDoctorId(@Param("doctorId")long doctorId);
	
	@Query(value="SELECT * FROM unavailable_time WHERE comment != '진료예약' AND doctor_id =:doctorId", nativeQuery=true)
	List<UnavailableTime> findAllByDoctorIdNoReservation(@Param("doctorId")long doctorId);
	
	@Query(value="SELECT * FROM unavailable_time WHERE doctor_id= :doctorId AND DATE=:date AND TIME=:time;", nativeQuery=true)
	UnavailableTime findTimeByDoctorIdNDatetime(@Param("doctorId")long doctorId, @Param("date") String string, @Param("time") String string2);

	@Query(value="delete FROM unavailable_time WHERE doctor_id= :doctorId AND DATE=:date", nativeQuery=true)
	void deleteAllByIdDate(@Param("doctorId")Long doctorId, @Param("date")Date date);
	
}
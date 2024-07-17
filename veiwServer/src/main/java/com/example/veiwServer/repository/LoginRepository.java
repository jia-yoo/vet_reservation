package com.example.veiwServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.veiwServer.dto.IMemberEditDto;
import com.example.veiwServer.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
	
	public boolean existsByUsername(String username);
	
	public Login findByUsername(String username);

	@Query(value = "delete from login WHERE member_id= :id", nativeQuery = true)
	public void deleteByMemberId(@Param("id")Long id);
	
	
	@Query(value ="SELECT l.username, l.password, m.address, m.phone, m.email, m.name, m.nickname "
			+ "FROM login l "
			+ "LEFT JOIN member m "
			+ "ON l.member_id = m.id "
			+ "WHERE member_id = :memberId;" , nativeQuery = true)
	public List<IMemberEditDto> findByMemberIdEditUser(@Param("memberId") String memberId);
	
	@Query(value ="SELECT l.username, l.password, m.address, m.phone, m.email,m.business_number,m.hospital_name,m.representative,m.business_hours,m.partnership,m.introduction "
			+ "FROM login l "
			+ "LEFT JOIN member m "
			+ "ON l.member_id = m.id "
			+ "WHERE member_id = :memberId;", nativeQuery = true)
	public List<IMemberEditDto> findByMemberIdEditHospital(@Param("memberId") String memberId);
}
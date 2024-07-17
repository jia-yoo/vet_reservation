package com.example.veiwServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.veiwServer.entity.Member;
import com.example.veiwServer.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
	
	
	@Query(value="select * from pet where member_id=:memberId", nativeQuery=true)
	public List<Pet> findAllByMemberId(@Param("memberId") Long memberId);
	
	
	
	public List<Pet> findByMember(Member member);
	
	
}

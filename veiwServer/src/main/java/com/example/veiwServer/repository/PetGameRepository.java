package com.example.veiwServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veiwServer.entity.Member;
import com.example.veiwServer.entity.PetGame;

public interface PetGameRepository extends JpaRepository<PetGame, Long> {

	Optional<PetGame> findByUser(Member user);

	Optional<PetGame> findByUserId(Long user);

	Optional<PetGame> findByUserAndIsFullyGrownFalse(Member user);
}
package com.example.veiwServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.veiwServer.entity.ChatRoom;
import com.example.veiwServer.entity.Member;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{
	public ChatRoom findByUserAndHospital(Member user, Member hospital);
	
	public List<ChatRoom> findByUser(Member user);
    
	public List<ChatRoom> findByHospital(Member hospital);
	
	
	
	@Query(value = "SELECT c.*, cr.* " +
            "FROM chat c " +
            "JOIN chat_room cr ON c.chat_room_id = cr.id " +
            "WHERE cr.user_id = :userId " +
            "ORDER BY c.send_date DESC", nativeQuery = true)
	List<Object[]> findChatsAndRoomsByUserId(Long userId);

	
	
}

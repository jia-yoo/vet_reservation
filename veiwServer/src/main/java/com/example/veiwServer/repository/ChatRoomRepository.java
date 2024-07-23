package com.example.veiwServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.veiwServer.entity.Chat;
import com.example.veiwServer.entity.ChatRoom;
import com.example.veiwServer.entity.Member;


public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{
	
	public ChatRoom findByUserAndHospital(Member user, Member hospital);
	
	public ChatRoom findByHospitalAndUser(Member hospital, Member user);
	
	public List<ChatRoom> findByUser(Member user);
    
	public List<ChatRoom> findByHospital(Member hospital);
	
	@Query("SELECT c FROM Chat c WHERE c.chatRoom.id = :chatRoomId ORDER BY c.sendDate DESC")
    List<Chat> findChatsByChatRoomId(@Param("chatRoomId") Long chatRoomId);
	
	
	//@Query("SELECT cr FROM ChatRoom cr WHERE (cr.member1 = :member1 AND cr.member2 = :member2) OR (cr.member1 = :member2 AND cr.member2 = :member1)")
    //ChatRoom findByMembers(@Param("member1") Member member1, @Param("member2") Member member2);
	
	
	
	@Query("SELECT cr FROM ChatRoom cr WHERE (cr.user = :member1 AND cr.hospital = :member2) OR (cr.user = :member2 AND cr.hospital = :member1)")
    ChatRoom findByMembers(@Param("member1") Member member1, @Param("member2") Member member2);
	
	
	
}

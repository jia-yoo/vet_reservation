package com.example.veiwServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.veiwServer.dto.IChatRoomListDto;
import com.example.veiwServer.entity.Chat;
import com.example.veiwServer.entity.ChatRoom;


public interface ChatRepository extends JpaRepository<Chat, Long> {
	//채팅방 채팅들 불러오기
	public List<Chat> findByChatRoom(ChatRoom chatRoom);
	
	@Query(value = "SELECT c.* FROM chat c " +
            "JOIN chat_room cr ON c.chat_roomid = cr.id " +
            "WHERE cr.id = :chatRoomID " +
            "ORDER BY c.send_date DESC", 
            nativeQuery = true)
	List<Chat> findChatsByChatRoomId(@Param("chatRoomID") Long chatRoomID);
	

	
	@Query(value = "SELECT cr.id AS chat_room_id, cr.user, cr.hospital, c.message, c.send_date " +
            "FROM chat_room cr " +
            "JOIN chat c ON c.chat_roomid = cr.id " +
            "WHERE (cr.user=:memberId OR cr.hospital=:memberId) " +
            "AND c.send_date = (SELECT MAX(c2.send_date) FROM chat c2 WHERE c2.chat_roomid = cr.id) " +
            "ORDER BY c.send_date DESC", 
            nativeQuery = true)
	List<IChatRoomListDto> findChatsAndRoomsByMemberId(@Param("memberId") Long memberId);
	//header의 memberId에 따라 채팅방 리스트 불러오기
	
	
}

package com.example.veiwServer.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.veiwServer.entity.Chat;
import com.example.veiwServer.entity.ChatRoom;
import com.example.veiwServer.entity.Member;
import com.example.veiwServer.repository.ChatRepository;
import com.example.veiwServer.repository.ChatRoomRepository;
import com.example.veiwServer.repository.MemberRepository;

@Service
public class ChatService {
	@Autowired
	private ChatRoomRepository chatroomRepo;
	
	@Autowired
	private ChatRepository chatRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	

	//채팅방 리스트 불러오기
	//추후 유저에 따른 채팅방 분리
	public List<ChatRoom> chatList(Long memberId){
		Member member = memberRepo.findById(memberId).get();
		
		List<ChatRoom> chatRoomList = chatroomRepo.findByUser(member);
		
		return chatRoomList;
	}
	
	//채팅방 생성
	//@PostMapping("/chatroom")
//	public ChatRoom createChatRoom(Member user, Member hospital){
//		ChatRoom chatRoom = new ChatRoom();
//		chatRoom.setUser(user);
//		chatRoom.setHospital(hospital);
//		chatroomRepo.save(chatRoom);
//		
//		return chatRoom;
//	}
	
	
	
	//채팅 내역 불러오기
	public List<Chat> getMessages(ChatRoom chatRoom){
		List<Chat> chatMessages = chatRepo.findByChatRoom(chatRoom);
		return chatMessages;
	}
	
	
	//메시지 전송
	public Chat saveMessage(Long senderId, Long receiverId, String message) {
	    Member sender = memberRepo.findById(senderId).orElseThrow(() -> new IllegalArgumentException("Sender not found with id: " + senderId));
	    Member receiver = memberRepo.findById(receiverId).orElseThrow(() -> new IllegalArgumentException("Receiver not found with id: " + receiverId));

	    // 채팅방 데이터가 있는지 확인하고, 없으면 새로 생성
	    ChatRoom chatRoom = getOrCreateChatRoom(sender, receiver);

	    Chat chatMessage = new Chat();
	    chatMessage.setChatRoom(chatRoom);
	    chatMessage.setSender(sender);
	    chatMessage.setReceiver(receiver);
	    chatMessage.setIsRead(false);
	    chatMessage.setMessage(message);
	    chatMessage.setSendDate(LocalDateTime.now());

	    return chatRepo.save(chatMessage);
	}

	public ChatRoom getChatRoom(Member sender, Member receiver) {
	    return chatroomRepo.findByUserAndHospital(sender, receiver);
	}

	private ChatRoom getOrCreateChatRoom(Member sender, Member receiver) {
	    ChatRoom chatRoom = chatroomRepo.findByUserAndHospital(sender, receiver);
	    if (chatRoom != null) {
	        return chatRoom;
	    } else {
	        // 채팅 방이 없으면 새로 생성
	        ChatRoom newChatRoom = new ChatRoom();
	        newChatRoom.setUser(sender);
	        newChatRoom.setHospital(receiver);
	        return chatroomRepo.save(newChatRoom);
	    }
	}
	
	
	
}

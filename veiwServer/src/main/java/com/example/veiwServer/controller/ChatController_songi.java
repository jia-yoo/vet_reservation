package com.example.veiwServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.veiwServer.entity.Chat;
import com.example.veiwServer.entity.ChatRoom;
import com.example.veiwServer.service.ChatService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ChatController_songi {
	
	@Autowired
	private ChatService chatService;
	
		
//	@MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public Chat sendMessage(@Payload Chat chatMessage) {
//        ChatRoom chatRoom = chatService.getChatRoom(chatMessage.getSender(), chatMessage.getReceiver());
//        
//        return chatService.sendMessage(chatRoom, chatMessage.getSender(), chatMessage.getMessage(), chatMessage.getReceiver());
//    }

	
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Chat addUser(@Payload Chat chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        //Add user to the WebSocket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender().getName());
        return chatMessage;
    }
    
    
    
    @GetMapping("/user/chatList")
    public ResponseEntity<?> chatList(HttpServletRequest request){
       String memberIdHeader = request.getHeader("MemberId");
 	   String authHeader = request.getHeader("Authorization");

 	   if (memberIdHeader == null || authHeader == null) {
     	   String errorMessage = "MemberId 또는 Authorization 헤더가 없습니다.";
     	    return ResponseEntity.badRequest().body(errorMessage);
        }
 	   Long memberId = Long.parseLong(memberIdHeader);
 	   
 	   List<ChatRoom> chatRoomList = chatService.chatList(memberId);
    	
 	   System.out.println("채팅방목록 출력 : " + chatRoomList);
 	   
 	   return ResponseEntity.ok(chatRoomList);
 	   
    }
    
	

}

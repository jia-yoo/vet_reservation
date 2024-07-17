package com.example.veiwServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.veiwServer.repository.LoginRepository;
import com.example.veiwServer.repository.MemberRepository;

@Service
public class ValidationService {

	@Autowired
	LoginRepository loginRepository;
	@Autowired
	MemberRepository memberRepository;
	
	
	public boolean checkUsername(String username) {
		return loginRepository.existsByUsername(username);
	}
	
	public boolean checkNickname(String nickname) {
		return memberRepository.existsByNickname(nickname);
	}
	
	public boolean checkNicknameEdit(String nickname,String memberId) {
		
		int result = memberRepository.existsByNicknameEdit(nickname, memberId);

		if(result == 1) {
			return true;
		}
		return false;
	}
}

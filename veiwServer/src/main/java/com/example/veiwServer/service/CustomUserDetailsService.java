package com.example.veiwServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.veiwServer.entity.Login;
import com.example.veiwServer.entity.Member;
import com.example.veiwServer.filter.UserNotApprovedException;
import com.example.veiwServer.repository.LoginRepository;
import com.example.veiwServer.repository.MemberRepository;
import com.example.veiwServer.security.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginRepository loginRepository;
    @Autowired
	MemberRepository memberRepository;

    public CustomUserDetailsService( LoginRepository loginRepository) {

        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				//DB에서 조회 
        Login userData = loginRepository.findByUsername(username);
        
        Member member = memberRepository.findById(userData.getMember().getId()).get();

        if(!(member.getStatus().equals("승인"))) {
        	if(member.getStatus().equals("대기")) {
        		throw new UserNotApprovedException("아직 승인되지 않았습니다. " );
        	}
        	throw new UserNotApprovedException("로그인 할 수 없습니다. " );
        }
        if (userData != null) {
						
						//UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(userData);
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
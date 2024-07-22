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
    public UserDetails loadUserByUsername(String username ) throws UsernameNotFoundException {
				
				//DB에서 조회 
        Login userData = loginRepository.findByUsername(username);
        
       
        if (userData != null) {
        	 Member member = memberRepository.findById(userData.getMember().getId()).get();
             System.out.println(member);
             	
            return new CustomUserDetails(userData);
        }
        System.out.println("User not found with username");
        throw new UsernameNotFoundException("notFound");
    }
}
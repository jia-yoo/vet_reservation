package com.example.veiwServer.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.veiwServer.repository.PetRepository;

@Service
public class PetService {
	@Autowired
    private PetRepository petRepository;
	
    
}

package com.example.veiwServer.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.example.veiwServer.dto.FcmSendDto;

@Service
public interface FcmService {


	int sendMessageTo(FcmSendDto fcmSendDto) throws IOException;
}

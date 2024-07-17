package com.example.veiwServer.controller.hospital;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.veiwServer.entity.Support;
import com.example.veiwServer.repository.SupportRepository;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalQnAListController_jia {

	@Autowired
	private SupportRepository supportRepo;
	
	@GetMapping("/qna")
	public List<Support> getMyQnAList(HttpServletRequest request) {
		Long userId = Long.parseLong(request.getHeader("MemberId"));
		return supportRepo.findAllByMemberId(userId);
	}
}

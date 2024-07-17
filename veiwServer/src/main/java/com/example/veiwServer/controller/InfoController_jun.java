package com.example.veiwServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.veiwServer.dto.IMemberEditDto;
import com.example.veiwServer.dto.MemberEditDto;
import com.example.veiwServer.entity.Doctor;
import com.example.veiwServer.repository.DoctorRepository;
import com.example.veiwServer.service.JoinService;

@RestController
@RequestMapping("/api/v1")
public class InfoController_jun {
	
	@Autowired
	JoinService joinService;
	@Autowired
	DoctorRepository doctorRepository;
	
	//병원정보 가져오기
	@GetMapping("/hospital/hospitalInfo/{MemberId}")
	public ResponseEntity<?> getHospitalInfo(@PathVariable("MemberId") String MemberId){
		System.out.println("병원정보 컨트롤러 들어옴");
		List<IMemberEditDto> result =joinService.getEditHospitalInfo(MemberId);
		System.out.println(result);
		return ResponseEntity.ok().body(result);
	}
	//병원정보 수정하기
	@PutMapping("/hospital/hospitalInfo")
	public ResponseEntity<?> UpdateHospitalInfo(@ModelAttribute MemberEditDto memberEditDto){
		System.out.println("병원정보 수정 컨트롤러 들어옴");
		System.out.println(memberEditDto);
		joinService.updateEditHospitalInfo(memberEditDto);
		return ResponseEntity.ok().body("수정하였습니다.");
	}
	//의사정보 가져오기
	@GetMapping("/hospital/doctorInfo/{MemberId}")
	public ResponseEntity<?> getDoctorInfo(@PathVariable("MemberId") String MemberId){
		System.out.println("의사정보 컨트롤러 들어옴");
		List<Doctor> doctors = doctorRepository.findAllByHospitalId(Long.parseLong(MemberId));
		System.out.println(doctors);
		return ResponseEntity.ok().body(doctors);
	}
	//유저정보 가져오기
	@GetMapping("/user/userInfo/{MemberId}")
	public ResponseEntity<?> getUserInfo(@PathVariable("MemberId") String MemberId){
		System.out.println("겟유저인포 컨트롤러 들어옴");
		List<IMemberEditDto> result =joinService.getEditUserInfo(MemberId);
		System.out.println(result);
		return ResponseEntity.ok().body(result);
	}
	//유저정보 수정하기
	@PutMapping("/user/userInfo")
	public ResponseEntity<?> UpdateUserInfo(@RequestBody MemberEditDto memberEditDto){
		System.out.println(memberEditDto);
		joinService.updateEditUserInfo(memberEditDto);
		return ResponseEntity.ok().body("수정하였습니다.");
	}
}

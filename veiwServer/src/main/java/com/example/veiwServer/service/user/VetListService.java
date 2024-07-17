package com.example.veiwServer.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.veiwServer.dto.MemVetDto;
import com.example.veiwServer.entity.Bookmark;
import com.example.veiwServer.entity.Member;
import com.example.veiwServer.repository.BookmarkRepository;
import com.example.veiwServer.repository.MemberRepository;
import com.example.veiwServer.repository.ReservationRepository;

@Service
public class VetListService {

	@Autowired 
	private MemberRepository memRepo;
	@Autowired 
	private ReservationRepository reserveRepo;
	@Autowired 
	private BookmarkRepository bookmarkRepo;
	
	
	public List<MemVetDto> getMemberVetList(String address, Long userId) {
		
		System.out.println("서비스");
		System.out.println(address);
		List<Member> result = memRepo.findMemberVetList(address);
		List<MemVetDto> MemVetList = new ArrayList<>();
		for(int i = 0; i<result.size(); i++) {
			Member hospital =  result.get(i);
			Long hospitalId = result.get(i).getId();
			MemVetDto mv = new MemVetDto();
			mv.setId(hospitalId);
			mv.setAddress(hospital.getAddress());
			mv.setPhone(hospital.getPhone());
			mv.setHospitalName(hospital.getHospitalName());
			mv.setRepresentative(hospital.getRepresentative());
			mv.setBusinessHours(hospital.getBusinessHours());
			mv.setBusinessNumber(hospital.getBusinessNumber());
			mv.setIntroduction(hospital.getIntroduction());
			mv.setPartnership(hospital.getPartnership());
			mv.setLogo(hospital.getLogo());
			mv.setEmail(hospital.getEmail());
			
			reserveRepo.findAvgReview(hospitalId);
			mv.setAvgReview(reserveRepo.findAvgReview(hospitalId));
			mv.setReview(reserveRepo.findReservWithReview(hospitalId));
			
			if(userId != 0L) {
				if(!bookmarkRepo.isBookmarked(hospitalId, userId).isEmpty()) {
					mv.setBookmarked(true);
				}else {
					mv.setBookmarked(false);
				}
			}
			MemVetList.add(mv);
		}
		return MemVetList;
	}
	
	public String isBookmarked(Long hosId, Long userId, Boolean isBookmarked) {
		 if(bookmarkRepo.isBookmarked(hosId, userId).isEmpty()) {
        	Bookmark newBookmark = new Bookmark();
        	newBookmark.setUser(memRepo.findById(userId).get());
        	newBookmark.setHospital(memRepo.findById(hosId).get());
        	bookmarkRepo.save(newBookmark);
        }else{
        	Bookmark oldBookmark = bookmarkRepo.isBookmarked(hosId, userId).get();
        	bookmarkRepo.delete(oldBookmark);
        };
		
		return "";
	}
	
	public List<MemVetDto> getMemberVet(String hospitalName, String address, Long userId) {
		List<Member> result = memRepo.findMemberVet(address, hospitalName);
		List<MemVetDto> MemVetList = new ArrayList<>();
		for(int i = 0; i<result.size(); i++) {
			Member hospital =  result.get(i);
			Long hospitalId = result.get(i).getId();
			MemVetDto mv = new MemVetDto();
			mv.setId(hospitalId);
			mv.setAddress(hospital.getAddress());
			mv.setPhone(hospital.getPhone());
			mv.setHospitalName(hospital.getHospitalName());
			mv.setRepresentative(hospital.getRepresentative());
			mv.setBusinessHours(hospital.getBusinessHours());
			mv.setBusinessNumber(hospital.getBusinessNumber());
			mv.setIntroduction(hospital.getIntroduction());
			mv.setPartnership(hospital.getPartnership());
			mv.setLogo(hospital.getLogo());
			mv.setEmail(hospital.getEmail());
			
			reserveRepo.findAvgReview(hospitalId);
			mv.setAvgReview(reserveRepo.findAvgReview(hospitalId));
			mv.setReview(reserveRepo.findReservWithReview(hospitalId));
			
			if(userId != 0L) {
				if(!bookmarkRepo.isBookmarked(hospitalId, userId).isEmpty()) {
					mv.setBookmarked(true);
				}else {
					mv.setBookmarked(false);
				}
			}
			MemVetList.add(mv);
		}
		return MemVetList;
	}
	
	public MemVetDto getVetDetail(Long hosId, Long userId) {
		Member hospital = memRepo.findById(hosId).get();
		
		MemVetDto mv = new MemVetDto();
		mv.setId(hosId);
		mv.setAddress(hospital.getAddress());
		mv.setPhone(hospital.getPhone());
		mv.setHospitalName(hospital.getHospitalName());
		mv.setRepresentative(hospital.getRepresentative());
		mv.setBusinessHours(hospital.getBusinessHours());
		mv.setBusinessNumber(hospital.getBusinessNumber());
		mv.setIntroduction(hospital.getIntroduction());
		mv.setPartnership(hospital.getPartnership());
		mv.setLogo(hospital.getLogo());
		mv.setEmail(hospital.getEmail());
		reserveRepo.findAvgReview(hosId);
		mv.setAvgReview(reserveRepo.findAvgReview(hosId));
		mv.setReview(reserveRepo.findReservWithReview(hosId));
		
		
		if(!bookmarkRepo.isBookmarked(hosId, userId).isEmpty()) {
			mv.setBookmarked(true);
		}
		
		
		return mv;
	}
}

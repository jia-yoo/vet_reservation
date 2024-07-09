package com.example.restServer.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restServer.dto.MemVetDto;
import com.example.restServer.entity.Bookmark;
import com.example.restServer.entity.Member;
import com.example.restServer.repository.BookmarkRepository;
import com.example.restServer.repository.MemberRepository;
import com.example.restServer.repository.ReservationRepository;

@Service
public class VetListService {

	@Autowired 
	private MemberRepository memRepo;
	@Autowired 
	private ReservationRepository reserveRepo;
	@Autowired 
	private BookmarkRepository bookmarkRepo;
	
	
	public List<MemVetDto> getMemberVetList(String address, Long userId) {
		List<Member> result = memRepo.findMemberVetList(address);
		List<MemVetDto> MemVetList = new ArrayList<>();
		System.out.println("serviceeee");
		System.out.println(address);
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
}
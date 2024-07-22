package com.example.veiwServer.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.veiwServer.dto.UserReservationDto;
import com.example.veiwServer.entity.Reservation;
import com.example.veiwServer.repository.ReservationRepository;

@RestController
@RequestMapping("/api/v1/user")
public class ReservationListController_jun {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@GetMapping("/reservationList/{memberId}")
	public ResponseEntity<?> reservationList(@PathVariable("memberId")Long memeberId){
		List<UserReservationDto> list =reservationRepository.findReserListByUserId(memeberId);
		for(UserReservationDto dto : list) {
			System.out.println(dto.getPet_name());
		}
		return ResponseEntity.ok().body(list);
	}
	
	@PutMapping("/reservation/cancel/{reservation_id}")
	public ResponseEntity<?> reservationCancel(@PathVariable("reservation_id") Long reservation_id){
		Reservation reservation =reservationRepository.findById(reservation_id).get();
		reservation.setStatus("취소");
		reservationRepository.save(reservation);
		return ResponseEntity.ok().body("");
	}
}

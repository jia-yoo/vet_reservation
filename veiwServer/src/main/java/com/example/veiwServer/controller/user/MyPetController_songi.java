package com.example.veiwServer.controller.user;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.veiwServer.dto.PetDto;
import com.example.veiwServer.entity.Member;
import com.example.veiwServer.entity.Pet;
import com.example.veiwServer.repository.MemberRepository;
import com.example.veiwServer.repository.PetRepository;
import com.example.veiwServer.service.user.PetService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user/mypage")
public class MyPetController_songi {
	
	@Autowired
    private PetService petService;
	
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private MemberRepository memberRepo;
	
	
	@Value("${spring.servlet.multipart.location}")
    private String uploadPath;
	
	
	@PostMapping("/myPet")
	public ResponseEntity<?> myPetRegist(PetDto petDto , @RequestParam("photo") MultipartFile file, HttpServletRequest request ) {
		   String memberIdHeader = request.getHeader("MemberId");
		   String authHeader = request.getHeader("Authorization");

		   if (memberIdHeader == null || authHeader == null) {
	    	   String errorMessage = "MemberId 또는 Authorization 헤더가 없습니다.";
	    	    return ResponseEntity.badRequest().body(errorMessage);
	       }

		   Long memberId = Long.parseLong(memberIdHeader);
		   Member member = memberRepo.findById(memberId).get();
		
	    try {
	    
	        Pet pet = new Pet();
	        pet.setBirthdate(petDto.getBirthdate());
	        pet.setGender(petDto.getGender());
	        pet.setHealthIssues(petDto.getHealthIssues());
	        pet.setIsNeutered(petDto.getIsNeutered());
	        pet.setName(petDto.getName());
	        pet.setType(petDto.getType());
	        pet.setBigtype(petDto.getBigtype());
	        pet.setWeight(petDto.getWeight());
	        pet.setMember(member);
	        
	        String imgOriginName = petDto.getFileName();
	        String imgNewName = UUID.randomUUID().toString() + "_" + imgOriginName;
	        System.out.println(imgOriginName + imgNewName);
	        
	        
	        pet.setPhoto(imgNewName);
	        
	        File imgFile = new File(pet.getPhoto());
	        petDto.getPhoto().transferTo(imgFile);
	        
	        
	        
	        
	        System.out.println(pet);
	        // Pet 객체를 데이터베이스에 저장
	        petRepository.save(pet);

	        return ResponseEntity.ok("success");

	   
	    } catch (Exception e) {
	        // 기타 예외 처리
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register pet: " + e.getMessage());
	    }
	}
	
	

	
	@GetMapping("/myPet")
	public ResponseEntity<?> myPetList(HttpServletRequest request) {
		
	   String memberIdHeader = request.getHeader("MemberId");
	   String authHeader = request.getHeader("Authorization");

	   if (memberIdHeader == null || authHeader == null) {
    	   String errorMessage = "MemberId 또는 Authorization 헤더가 없습니다.";
    	    return ResponseEntity.badRequest().body(errorMessage);
       }

	   Long memberId = Long.parseLong(memberIdHeader);
	   Member member = memberRepo.findById(memberId).get();


		List<Pet> petList = petRepository.findByMember(member);
		System.out.println("펫 목록 출력 : " + petList );
		
		return ResponseEntity.ok(petList);
	}
	
	
	
	@GetMapping("/myPet/{id}")
	public ResponseEntity<?> myPetDetail(@PathVariable("id") Long id, HttpServletRequest request) {
	   System.out.println("펫정보 불러오기 id값 출력: " + id);
	    
	   String memberIdHeader = request.getHeader("MemberId");
	   String authHeader = request.getHeader("Authorization");

	   if (memberIdHeader == null || authHeader == null) {
    	   String errorMessage = "MemberId 또는 Authorization 헤더가 없습니다.";
    	    return ResponseEntity.badRequest().body(errorMessage);
       }

	    
	    Optional<Pet> optionalPet = petRepository.findById(id);
	    if (optionalPet.isPresent()) {
	        Pet pet = optionalPet.get();
	        System.out.println("펫 상세정보 출력: " + pet);
	        return ResponseEntity.ok(pet);
	    } else {
	        System.out.println("펫을 찾을 수 없습니다: " + id);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	}
	
	
	@PutMapping("/myPet/{id}")
	public ResponseEntity<?> myPetEdit(@PathVariable("id")Long id,PetDto petDto, HttpServletRequest request) {
		 String memberIdHeader = request.getHeader("MemberId");
		   String authHeader = request.getHeader("Authorization");

		   if (memberIdHeader == null || authHeader == null) {
	    	   String errorMessage = "MemberId 또는 Authorization 헤더가 없습니다.";
	    	    return ResponseEntity.badRequest().body(errorMessage);
	       }
	       
	       
		try {
			Pet pet = petRepository.findById(id).get();
			
			pet.setBirthdate(petDto.getBirthdate());
	        pet.setGender(petDto.getGender());
	        pet.setHealthIssues(petDto.getHealthIssues());
	        pet.setIsNeutered(petDto.getIsNeutered());
	        pet.setName(petDto.getName());
	        pet.setType(petDto.getType());
	        pet.setBigtype(petDto.getBigtype());
	        pet.setWeight(petDto.getWeight());
			
			
	        petRepository.save(pet);
	        
	        return ResponseEntity.ok("success");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
		}
		
	}
	
	
	@DeleteMapping("/myPet/{id}")
	public ResponseEntity<String> myPetDelte(@PathVariable("id")Long id, HttpServletRequest request) {
	   String memberIdHeader = request.getHeader("MemberId");
	   String authHeader = request.getHeader("Authorization");

	   if (memberIdHeader == null || authHeader == null) {
    	   String errorMessage = "MemberId 또는 Authorization 헤더가 없습니다.";
    	    return ResponseEntity.badRequest().body(errorMessage);
       }

	   //Long memberId = Long.parseLong(memberIdHeader);
	   //Member member = memberRepo.findById(memberId).get();
		
		
		
		petRepository.deleteById(id);
		
		return ResponseEntity.ok("삭제 완료"); 
		
	}
	
}

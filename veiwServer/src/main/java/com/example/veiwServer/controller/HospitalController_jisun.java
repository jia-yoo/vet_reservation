package com.example.veiwServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hospital")
public class HospitalController_jisun {
	
	@RequestMapping("/reservList")
	public String reservList() {
		return "/hospital/reserv_list";
	}
	
	@RequestMapping("/reservDetail")
	public String reservDetail(@RequestParam("id")Long id, Model model) {
		model.addAttribute("reservId", id);
		return "/hospital/reserv_detail";
	}

	@RequestMapping("/reserveDocSelect")
	public String reserveDocSelect() {
		return "/hospital/reserve_doc_select";
	}
	
	@RequestMapping("/reserveCalender")
	public String reserveCalender(@RequestParam("doctorId")Long doctorId, @RequestParam("doctorName")String doctorName, Model model) {
		model.addAttribute("doctorId", doctorId);
		model.addAttribute("doctorName", doctorName);
		return "/hospital/reserve_calender";
	}
	
	@RequestMapping("/reserveSchedule")
	public String reserveSchedule(@RequestParam("doctorId")Long doctorId, @RequestParam("date")String date, @RequestParam("doctorName")String doctorName, Model model) {
		model.addAttribute("date", date);
		model.addAttribute("doctorId", doctorId);
		model.addAttribute("doctorName", doctorName);
		return "/hospital/reserve_schedule";
	}
	
}

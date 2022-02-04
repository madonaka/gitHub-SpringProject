package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.domain.PdsVO;
import org.zerock.service.PdsService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/pds/*")
@AllArgsConstructor
public class PdsController {
	
	private PdsService service;
	
	@GetMapping("/list")
	public void getlist(@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("list");
		model.addAttribute("list", service.getList(cri));
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(service.listCount());
		
		model.addAttribute("pageMaker", pm);	
	}
	
	@GetMapping("/write")
	public void write(Model model, @ModelAttribute("cri") Criteria cri ) {
		log.info("/write");
	}
	
	@PostMapping("/write")
	public String postWrite(PdsVO vo, MultipartFile[] uploadFile) {
		for(MultipartFile multipartFile : uploadFile) {
			log.info("--------------");
			log.info("upload file name : "+multipartFile.getOriginalFilename());
			log.info("upliad file size : "+multipartFile.getSize());
		}
		return "redirect:/pds/list";
	}
}

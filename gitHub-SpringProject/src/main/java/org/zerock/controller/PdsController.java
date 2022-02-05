package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.domain.PdsVO;
import org.zerock.service.PdsService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

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
	public void postWrite(MultipartFile[] uploadFile, PdsVO pds, RedirectAttributes rttr) {
		
		String uploadFolder ="C:\\Temp\\upload";
		
		//폴더만들기
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload path: "+uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("--------------");
			log.info("upload file name : "+multipartFile.getOriginalFilename());
			log.info("upliad file size : "+multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info("only file name: "+uploadFileName);
			
			//이름 랜덤으로 생성
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() +"_"+uploadFileName;
			
			//db등록 (파일첨부 안되면 null처리)
			if(multipartFile.getSize()!=0) {
				pds.setFilename(uploadFileName);
			}
			service.register(pds);
			
			//File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				//파일 이미지 타입 체크
				if(checkImagType(saveFile)) {
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "S_"+uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100,100);
					thumbnail.close();
				}
			
				multipartFile.transferTo(saveFile);
			}catch(Exception e) {
				log.error(e.getMessage());
			}
		}
		
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
	
	//이미지파일 검사
	private boolean checkImagType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}

package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.domain.PdsVO;
import org.zerock.domain.UploadVO;
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
	public String postWrite(MultipartFile[] uploadFile, PdsVO pds, RedirectAttributes rttr) {
		
		String uploadFolder ="C:\\Temp\\upload";
		String thumnailFolder ="C:\\Users\\ckdwn\\git\\gitHub-SpringProject\\gitHub-SpringProject\\src\\main\\webapp\\resources\\uploadthumnail";
		
		//???????????????
		//File uploadPath = new File(uploadFolder, getFolder());
		File uploadPath = new File(uploadFolder);
		File thumnailPath = new File(thumnailFolder);
		
		log.info("upload path: "+uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		if(thumnailPath.exists() == false) {
			thumnailPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("--------------");
			log.info("upload file name : "+multipartFile.getOriginalFilename());
			log.info("upliad file size : "+multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info("only file name: "+uploadFileName);
			
			//?????? ???????????? ??????
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() +"_"+uploadFileName;
			
			//db?????? (???????????? ????????? null??????)
			if(multipartFile.getSize()!=0) {
				pds.setFilename(uploadFileName);
			}
			service.register(pds);
			
			//File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				//?????? ????????? ?????? ??????
				if(checkImagType(saveFile)) {
					FileOutputStream thumbnail = new FileOutputStream(new File(thumnailPath, "S_"+uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100,100);
					thumbnail.close();
				}
			
				multipartFile.transferTo(saveFile);
			}catch(Exception e) {
				log.error(e.getMessage());
			}
		}
		return "redirect:/pds/list";
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
	
	//??????????????? ??????
	private boolean checkImagType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@GetMapping({"/get","/modify","/delete"})
	public void get( @RequestParam("bno") int bno, Model model, @ModelAttribute("cri") Criteria cri ) {
		log.info("/get, /modify, /delete");
		
		//??????????????? ??????
		if(service.get(bno).getFilename()!=null) {
			int fileidx = service.get(bno).getFilename().indexOf("_")+1;
			String filename = service.get(bno).getFilename().substring(fileidx);
			model.addAttribute("filename", filename);
		}
		
		model.addAttribute("pds", service.get(bno));
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> get(String filename ) {
		log.info("download file :" +filename);
		
		Resource resource = new FileSystemResource("C:\\Temp\\upload\\"+filename);
		
		log.info("resource: "+resource);
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Disposition", "attachment; filename="+new String(resourceName.getBytes("UTF-8"),"ISO-8859-1"));
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	

	@PostMapping("/modify")
	//oldfile????????? ?????? ??? newfile ??????
	//oldfile????????? newfile ??????
	public String modify(UploadVO pds
						,Criteria cri
						,RedirectAttributes rttr) {
		
		log.info("modify : " + cri);
		log.info("oldfile name : "+ pds.getOldfilename());
		log.info("bno : "+pds.getBno());
		log.info("title : "+pds.getTitle());
		log.info("content : "+pds.getContent());
		log.info("oldfilename??? ?????? : "+pds.getOldfilename().length());
		 
		
		log.info("modify : "+ pds);
		
		
		int bno = pds.getBno();
		String oldfilename = pds.getOldfilename();
				
		String uploadFolder ="C:\\Temp\\upload";
		String thumnailFolder ="C:\\Users\\ckdwn\\git\\gitHub-SpringProject\\gitHub-SpringProject\\src\\main\\webapp\\resources\\uploadthumnail";
	
		File uploadPath = new File(uploadFolder);
		File thumnailPath = new File(thumnailFolder);
		
		log.info("upload path: "+uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		if(thumnailPath.exists() == false) {
			thumnailPath.mkdirs();
		}
		
		for (MultipartFile newfilename :  pds.getNewfilename()) {
			//newfile?????? ??? 
			if(newfilename.getOriginalFilename() != null) {
				log.info("newfilename ????????? ???????????????"+newfilename.getOriginalFilename());
				
				UUID uuid = UUID.randomUUID();
				String filename = uuid.toString() +"_"+newfilename.getOriginalFilename();
				
				log.info("???! ???????????? newfilename??? "+filename+" ?????????");
				
				//oldfile??? ??????
				if(oldfilename.length() != 0) {
					log.info("newfilename??? ???????????????, oldfilename??? ???????????????");
					deleteFiles(bno);
				}
				log.info("?????? newfilename??? ???????????????.");
				
				//???????????? DB??????
				if(newfilename.getSize()!=0) {
					pds.setFilename(filename);
				}
				service.modify(pds);
				
				try {
					File saveFile = new File(uploadPath, filename);
					newfilename.transferTo(saveFile);
					
					//?????? ????????? ?????? ??????
					if(checkImagType(saveFile)) {
						FileOutputStream thumbnail = new FileOutputStream(new File(thumnailPath, "S_"+filename));
						Thumbnailator.createThumbnail(newfilename.getInputStream(), thumbnail, 100,100);
						thumbnail.close();
					}
				
					newfilename.transferTo(saveFile);
				}catch(Exception e) {
					log.error(e.getMessage());
				}
			}
		}
		return "redirect:/pds/list";
	}
	
	
	//??????????????????
	private void deleteFiles(int bno) {
		log.info("deleteFiles bno : "+ bno);
		String filename = service.get(bno).getFilename();
		if(filename == null) {
			return;
		}
		log.info("delete attach file...........");
		log.info(filename);
		
		try {
			Path file = Paths.get("C:\\Temp\\upload\\"+filename);
			Files.deleteIfExists(file);
			
			if(Files.probeContentType(file).startsWith("image")) {
				Path thumbnail = Paths.get("C:\\Users\\ckdwn\\git\\gitHub-SpringProject"
						+ "\\gitHub-SpringProject\\src\\main\\webapp\\resources"
						+ "\\uploadthumnail\\S_"+filename);
				Files.delete(thumbnail);
			}
		}catch(Exception e) {
			log.error("delete file error"+e.getMessage());
		}
	}
	//??????????????????
	private int fileExists(String filename) {
		File file = new File("C:\\Temp\\upload\\"+filename);
		int row = 0;
		if (file.exists()) {
			row = 1;
		}
		return row;
	}
	
	@PostMapping("/delete")
	public String remove(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) {
		log.info("delete...."+bno);
		boolean row = false;
		
		deleteFiles(bno);
		
		String filename = service.get(bno).getFilename();
		if(fileExists(filename) == 0) {
			row = service.delete(bno);
		
			rttr.addFlashAttribute("result", row);
		}
		rttr.addFlashAttribute("result", row);
		return "redirect:/pds/list";
	}
	
}

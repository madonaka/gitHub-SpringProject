package org.zerock.domain;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadVO extends BoardVO{
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
	private String userid;
	private List<MultipartFile> newfilename;
	private String oldfilename;
	private String filename;
}

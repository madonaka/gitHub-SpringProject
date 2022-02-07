package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum; //페이지 번호
	private int amount; // 한 페이지에 표시되는 개수
	
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1,10);
	}
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	/*public String[] getTypeArr() {
		
		return type == null ? new String[] {} : type.split("");
	}*/
	/*  public String[] getTypeArr() {
		    
		    return type == null? new String[] {}: type.split("");
		  }*/
	public String toString() {
		return "Criteria [type="+type+", keyword="+keyword+"]";
	}
	
	public String getListlink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
}

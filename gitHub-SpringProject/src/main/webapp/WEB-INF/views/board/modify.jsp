<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


   <head><title>게시판 수정</title>
    <link rel="stylesheet" type="text/css" href="/stylesheet.css">
<script>
	function send(){
		if(modi.title.value == ""){
			alert("제목을 입력해주세요");
			modi.title.focus();
			return;
		}
		if(modi.content.value == ""){
			alert("내용을 입력해주세요");
			modi.content.focus();
			return;
		}
		
		modi.submit();
		//modify.action = "/board/modify";
	}
	function reset(){
		modi.reset();
		modi.title.focus();
	}
</script>
</head>
<%@ include file= "../include/topmenu.jsp"  %>

 <body topmargin="0" leftmargin="0">
 <table border="0" width="800">
 <tr>
   <td width="20%" height="500" bgcolor="#ecf1ef" valign="top">
<%@ include file= "../include/login_form.jsp"  %>

   <!-- 다음에 추가할 부분 "-->

   </td>
   <td width="80%" valign="top">&nbsp;<br>
     <img src="/resources/img/bullet-01.gif"><font size="3" face="돋움" color="blue"> <b>자 유 게 시 판</b></font>
     <font size="2"> - 수정하기</font><p>
     <img src="/resources/img/bullet-03.gif"><font size="2" face="돋움" color="orange"> 잠깐</font> &nbsp;
     <img src="/resources/img/bullet-02.gif"><font size="2" face="돋움">는 필수 입력 사항입니다.</font><p>
     
     
     <form name="modi" method="post" action="/board/modify">
     	<input type="hidden" name="bno" value="${board.bno }">
      <table border="0">
       <tr>
         <td width="5%" align="right"><img src="/resources/img/bullet-02.gif"></td>
         <td width="15%"><font size="2" face="돋움">글쓴이</font></td>
         <td width="80%">
         <input type="text" size="20" name="writer" readOnly value="<c:out value='${board.writer }'/>" ></td>
       </tr>
       <tr>
         <td align="right">&nbsp;</td>
         <td ><font size="2" face="돋움">메일주소</font></td>
         <td>
          <input type="text" size="20" name="email" value=""></td>
       </tr>
       <tr>
         <td align="right"><img src="/resources/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">제 목</font></td>
         <td><input type="text" size="60" name="title" value="<c:out value='${board.title }'/>"></td>
       </tr>
       <tr>
         <td align="right"><img src="/resources/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">내용</font></td>
         <td><textarea wrap="physical" rows="10" name="content" cols="60" ><c:out value="${board.content }"/></textarea></td>
       </tr>
       <tr>
         <td align="right"><img src="/resources/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">비밀번호</font></td>
          <td><input type="password" size="10" name="pass" >
		  <font size="2" face="돋움">*.수정과 삭제시 꼭 입력하셔야 합니다.</font></td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td><font size="2">&nbsp;</font></td>
          <td><a href="javascript:send()"><img src="/resources/img/edit2.gif" border=0></a>&nbsp;
          <a href="javascript:reset()"><img src="/resources/img/cancle.gif" border=0></a></td></tr>
      </table>
      </form>
    </td></tr>
  </table>
  </body>
  <%@ include file= "../include/copyright.jsp"  %>
  
  </html>

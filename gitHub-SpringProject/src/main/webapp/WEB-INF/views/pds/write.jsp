<%@ page contentType="text/html; charset=UTF-8" %>

<html>
   <head><title>게시판 작성</title>
    <link rel="stylesheet" type="text/css" href="/stylesheet.css">
<script>
	function send(){
		if(pds.writer.value==""){
			alert("작성자를 입력하세요");
			pds.writer.focus();
			return;
		}
		if(pds.content.value==""){
			alert("내용을 입력하세요");
			pds.content.focus();
			return;
		}
		alert("게시합니다");
		pds.submit();
	};
	function reset(){
		pds.reset();
	};
</script>
</head>
<%@ include file= "../include/topmenu.jsp"  %>

 <body topmargin="0" leftmargin="0">
 <table border="0" width="800">
 <tr>
   <td width="20%" height="500" bgcolor="#ecf1ef" valign="top">
<%@ include file= "../include/login_form.jsp"  %>

   <!-- 다음에 추가할 부분 -->

   </td>

   <td width="80%" valign="top">&nbsp;<br>
     <img src="/resources/img/bullet-01.gif"><font size="3" face="돋움" color="blue"> <b>반갑습니다</b></font>
     <font size="2"> - 글쓰기</font><p>
     <img src="/resources/img/bullet-03.gif"><font size="2" face="돋움" color="orange"> 잠깐</font> &nbsp;
     <img src="/resources/img/bullet-02.gif"><font size="2" face="돋움">는 필수 입력 사항입니다.</font><p>
     
     <form name="pds" method="post" action="/pds/write" enctype="multipart/form-data" >
		<!--<c:if test="${session !=null }">
		<input type="hidden" name="userid" value="${session.userid }">
		</c:if>-->
		
	  <table border="0">
       <!--
       <tr>
         <td width="5%" align="right"><img src="/resources/img/bullet-02.gif"></td>
         <td width="15%"><font size="2" face="돋움">글쓴이</font></td>
         <td width="80%">
         <input type="text" size="20" name="writer" value="${session.userid }" ></td>
       </tr>
	   <tr>
         <td align="right"><img src="/resources/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">제목</font></td>
         <td><input type="text" size="60" name="title" ></td>
       </tr>
       <tr>
         <td align="right"><img src="/resources/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">내용</font></td>
         <td><textarea wrap="physical" rows="10" name="content" cols="60"></textarea></td>
       </tr>-->
       <tr>
         <td align="right"><img src="/resources/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">첨부파일</font></td>
         <td><input type="file" size="60" name="uploadFile" multiple></td>
       </tr>
        <tr></tr>
		<tr>
          <td align="right">&nbsp;</td>
          <td><font size="2">&nbsp;</font></td>
          <td>
          	<button>submit</button>
             <a href="javascript:send()"><img src="/resources/img/save.gif" border=0></a>&nbsp;&nbsp;&nbsp;
             <a href="javascript:reset()"><img src="/resources/img/cancle.gif" border=0></a>
          </td>
        </tr>
      </table>
      </form>
    </td>
  </tr>
  </table>
  </body>
  <%@ include file= "../include/copyright.jsp"  %>
  
  </html>

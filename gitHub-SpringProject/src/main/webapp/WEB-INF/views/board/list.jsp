<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

<c:if test="${row != null}">
<script>
	var row = '<c:out value="${row}"/>';
	alert(row);	
</script>
</c:if>

<script>
	function searchSend(){
		if(searchForm.type.value==""){
			alert("검색종류를 선택하세요");
			return
		}
		if(searchForm.keyword.value==""){
			alert("검색어를 입력하세요");
			return
		}
		searchForm.submit();
	}
</script>
<html>
<head><title>게시판 읽기</title>
<link rel="stylesheet" type="text/css" href="/stylesheet.css">
<style type="text/css">
  a.list {text-decoration:none;color:black;font-size:10pt;}
</style>
</head>

<body bgcolor="#FFFFFF" topmargin="0" leftmargin="0">
<%@ include file= "../include/topmenu.jsp"  %>

<table border="0" width="800">
  <tr>
    <td width="20%" height="500" valign="top" bgcolor="#ecf1ef">

<%@ include file= "../include/login_form.jsp"  %>

	<!-- 다음에 추가할 부분 --></td>

	<td width="80%" valign="top">	
		<br>
    <table border="0" cellspacing="1" width="100%" align="center">
      <tr>
        <td colspan="7" align="center" valign="center" height="20">
        <font size="4" face="돋움" color="blue">
        <img src="/resources/img/bullet-01.gif"> <b>자 유 게 시 판</b></font></td></tr>
      <tr>
        <td colspan="5" align="right" valign="middle" height="20">
		<font size="2" face="고딕">전체 : <b>15</b>건 - 1/ 2 Pages</font></td></tr>
 	   <tr bgcolor="e3e9ff">
 	      <td width="10%" align="center" height="20"><font face="돋움" size="2">번 호</font></td>
 	      <td width="50%" align="center" height="20"><font face="돋움" size="2">제 목</font></td>
 	      <td width="15%" align="center" height="20"><font face="돋움" size="2">글쓴이</font></td>
 	      <td width="15%" align="center" height="20"><font face="돋움" size="2">작성일</font></td>
 	      <td width="10%" align="center" height="20"><font face="돋움" size="2">조회수</font></td>
 	   </tr>
 	   
 	   <c:if test="${list !=null}">
 	   <c:forEach var="board" items="${list }">
		<tr onMouseOver="style.backgroundColor='#D1EEEE'" onMouseOut="style.backgroundColor=''">
			<td align="center" height="25">
			<font face="돋움" size="2" color="#000000">${board.bno }</font></td>
			<td align="left" height="20">&nbsp;
				<font face="돋움" size="2" color="#000000">
				<a class="list" href="/board/get?bno=${board.bno}">${board.title }</a></td>
					<td align="center" height="20"><font face="돋움" size="2">
					<a class="list" href="mailto:ein1027@nate.com">${board.writer }</a></font></td>
				<td align="center" height="20"><font face="돋움" size="2"><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd hh:mm"/></font></td>
				<td align="center" height="20"><font face="돋움" size="2">
				3</font></td>
		</tr>
		</c:forEach>
		</c:if>
		<c:if test="${list ==null }">
		<tr onMouseOver="style.backgroundColor='#D1EEEE'" onMouseOut="style.backgroundColor=''">
			<td align="center" height="25">
			<font face="돋움" size="2" color="#000000">5</font></td>
			<td align="left" height="20">&nbsp;
				<font face="돋움" size="2" color="#000000">
				<a class="list" href="">제목부분입니다</a></td>
					<td align="center" height="20"><font face="돋움" size="2">
					<a class="list" href="mailto:ein1027@nate.com">나종민</a></font></td>
				<td align="center" height="20"><font face="돋움" size="2">2007-10-22</font></td>
				<td align="center" height="20"><font face="돋움" size="2">
				3</font></td>
		</tr>
		</c:if>


	 <div align="center">
        <table width="700" border="0" cellspacing="0" cellpadding="5">
          <tr>&nbsp;</tr><tr>
             <td colspan="5">        
                <div align="center">
                	<c:if test="${pageMaker.prev}">
                		<a href="/board/list${pageMaker.makeQuery(pageMaker.startPage - 1)}"> 이전</a>
                	</c:if>
                	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				    	<a href="/board/list${pageMaker.makeQuery(idx)}">${idx}</a></li>
				    </c:forEach>
				    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				    	<a href="/board/list${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
				    </c:if>               
                
                </div>
			  </td>
			 </tr>
		</table>

	<table width="600">
		<tr>
			<td width="25%"> &nbsp;</td>
			<td width="50%" align="center">
				<table>
					<form name="searchForm" action="/board/list" method="get">	
					<!-- 검색어를 이용하여 글제목, 작성자, 글내용 중에 하나를 입력 받아 처리하기 위한 부분 -->
						<tr>
							<td>
								<select name="type">
									<option value="" <c:out value="${cri.type == null ? 'seleted':''}"/>>검색조건</option>
									<option value="t"<c:out value="${cri.type eq 't' ? 'seleted':''}"/> >글제목</option>
									<option value="w"<c:out value="${cri.type eq 'w' ? 'seleted':''}"/>>작성자</option>
									<option value="c"<c:out value="${cri.type eq 'c' ? 'seleted':''}"/>>글내용</option>
								</select>
							</td>
							<td> <input type="text" size=20 name="keyword"/>
							<input type="hidden" name="pageNem" value='${pageMaker.cri.pageNum }' />
							<input type="hidden" name="amount" value='${pageMaker.cri.amount}'/>
							<td> <button onclick="searchSend()">검색</button>
							<a href="#"><img src="/resources/img/search2.gif" border="0"></a></td>
						</tr>
					</form> -->
				</table>
			</td>
			<td width="25%" align="right">
			<a href="#"><img src="/resources/img/write.gif" border="0"></a>
			</td>
		</tr>
	</table>
</body>
 <%@ include file= "../include/copyright.jsp"  %>

</html>


<%@ page contentType="text/html; charset=UTF-8" %>


<html>
<head>
 <title>Login</title>
<script>
	//컨트롤러에 post 방식으로 넘기기
	function goPost(url){
		let f = document.createElement('form');
		f.setAttribute('method','post');
		f.setAttribute('action',url);
		document.body.appendChild(f);
		f.submit();
	}


	function check_login(){
		if(login_form.userid.value==""){
			alert("아이디를 입력하세요.");
			login_form.userid.focus();
			return;
		}
		if(login_form.password.value==""){
			alert("비밀번호를 입력하세요.");
			login_form.password.focus();
			return;
		}
		login_form.submit();
	}
</script>
 </head>

 <body>
 <c:if test="${session == null }">
 <div>
	<table width="100%" height="120" border="0">
	   <form name="login_form" action="/member/userlogin_form" method="post" >
	     <tr>
	       <td colspan="2" bgcolor="#6FA0E" height="20" align="center">
	         <font size="2" color="white"><b>Member Login</b></font>
	       </td>
	     </tr>
	     <tr>
	       <td ><font size="2">아 이 디</font></td>
	       <td ><input type="text" size="10" name="userid"></td>
	     </tr>
	     <tr>
	       <td><font size="2">비밀번호</font></td>
				 <td>
	         <input type="password" size="10" name="password">
	       </td>
	     </tr>
	     <tr>
	       <td><input type="image" src="/resources/img/login1.gif" border="0" onClick="check_login()"></td>
				 <td>
	           <a href="/member/userinfo_insert"><img src="/resources/img/regist.gif" border="0"></a>
	       </td>
	     </tr>
	 </form>
	 </table>
 </div>
 </c:if>
 <c:if test="${session != null }">
 <div>
	 <table width="100%" height="120" border="0">
	   <tr>
	     <td bgcolor="#6FA0E" align="center" height="20">
	       <font size="2" color="white"><c:if test="${session != null}">${session.userid }</c:if></font>
	     </td>
	   </tr>
	   <tr>
	     <td align="center">
	       <font size="2">
	       <p><b>${session.name }</b> 님 환영합니다</p>
	       <a href="javascript:goPost('/member/logout')">로그오프</a><br>
	       <a href="#">회원정보수정</a><br>
	       </font>
	     </td>
	   </tr>
	 </table>
 </div>
 </c:if>
 </body>
 </html>

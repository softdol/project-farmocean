<%@page import="com.ezen.farmocean.member.dto.LoginMember"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<%@ include file="/resources/jspf/header.jspf"%>
</head>
<body>
	<%@ include file="/resources/jspf/body_header.jspf"%>

	<h1>로그인 성공!!</h1>

	<%
	LoginMember member1 = (LoginMember) session.getAttribute("loginId");
	%>


	<table border="1">
		<tr>
			<td>로그인 아이디</td>
			<td id="logined_id">[<%=member1.getMember_id()%>]님 환영해요.
			</td>
		</tr>
		<tr>
			<td>로그인 이름</td>
			<td id="logined_name">[<%=member1.getMember_name()%>]
			</td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td id="logined_nickName">[<%=member1.getMember_nickName()%>]
			</td>
		</tr>
		<tr>
			<td>회원 등급</td>
			<td id="logined_class">[<%=member1.getMember_type()%>]회원
			</td>
		</tr>
	</table>

	<br>
	<br>

	<button id="logout_btn">로그아웃 버튼</button>
	<button id="chat_btn">채팅 버튼</button>
	<button id="test_btn">test 버튼</button>
	


	<script>
		const logout = document.getElementById('logout_btn');
		const chat = document.getElementById('chat_btn');
		const test = document.getElementById('test_btn');
		
		logout.addEventListener('click',(e)=>{
			
		
		myWindow = window.open('https://nid.naver.com/nidlogin.logout', '네이버팝업', 
        'width=1, height=1, scrollbars=yes, resizable=no');
		
		setTimeout("myWindow.close()", 1000);
		setTimeout("window.location.replace('/farmocean/member/logout')", 1000);
		
		
		
		
		  
		
		
		});
		
		
		
		
		chat.addEventListener('click',(e)=>{
	
		window.location.href='/farmocean/echo/chat';
		});
	</script>
</body>


</html>
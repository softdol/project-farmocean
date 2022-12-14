<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/resources/jspf/header.jspf" %>
<title>회원 정보 수정</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>회원 정보 수정</h3>

	<table>

		<form action="changeinfo" method="POST" action="https://test.mobilians.co.kr/mcash_webnoti/step1.php" accept-charset="EUC-KR">

			<c:forEach items="${memberinfo }" var="info">

				<tr>
					<td>아이디</td>
					<td><input name="member_id" value="${info.member_id }"
						type="text" style="width: 500px;" readonly /></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input id="password" name="member_pw"
						value="${info.member_pw }" type="text" style="width: 500px;" />
						<div id="out1"></div></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input id="member_name" name="member_name" value="${info.member_name }"
						type="text" style="width: 500px;" readonly /></td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td><input id="nickname" name="member_nickName"
						value="${info.member_nickName }" type="text" style="width: 500px;" />
						<div id="out"></div></td>
				</tr>
				<tr>
					<td>포인트</td>
					<td><input name="member_point" value="${info.member_point }"
						type="text" style="width: 500px;" readonly /></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input id="email" name="member_email"
						value="${info.member_email }" type="text" style="width: 500px;" />
						<div id="out2"></div></td>
				</tr>
				<tr>
					<td>표시</td>
					<td><input id="member_type" name="member_type"
						value="${info.member_type }" type="text" style="width: 500px;"
						readonly /></td>
				</tr>
				<tr>
					<td>핸드폰 번호</td>
					<td><input id="phone" name="member_phoneNum"
						value="${info.member_phoneNum }" type="text" style="width: 500px;" />
						<div id="out3"></div></td>
				</tr>
				<tr>
					<td>현재계좌번호</td>
					<td><input id="member_accountNum" name="member_accountNum" value="${info.member_accountNum }" type="text" style="width: 500px;" readonly/></td>
				</tr>
				<tr>
				<td>계좌번호</td>
				<td><select id="bankName" name="post_member_bank">
						<option value="신한은행">신한은행</option>
						<option value="우리은행">우리은행</option>
						<option value="국민은행">국민은행</option>
						<option value="농협">농협</option>
						<option value="우체국">우체국</option>
				</select> <input type="text" id="bankNumber"
					placeholder="계좌번호를 입력해주세요."></td>
			</tr>
			<tr>
				<tr>
					<td>현재주소</td>
					<td><input id="member_address" name="member_address" value="${info.member_address }" type="text" style="width: 500px;" readonly /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button"
						onclick="sample6_execDaumPostcode()" value="우편번호 찾기"></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><input type="text" id="sample6_postcode" name="postcode"
						placeholder="우편번호" style="width: 500px;"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" id="sample6_address" name="address"
						placeholder="주소" style="width: 500px;"></td>
				</tr>
				<tr>
					<td>참고주소</td>
					<td><input type="text" id="sample6_extraAddress"
						name="extraAddress" placeholder="참고항목" style="width: 500px;"></td>
				</tr>
				<tr>
					<td>추가주소</td>
					<td><input type="text" id="sample6_detailAddress"
						name="detailAddress" placeholder="추가주소" style="width: 500px;"></td>
				</tr>
				<tr>
			</c:forEach>
			<td></td>
			<td><input id="subBtn" type="submit" value="내 정보 수정하기" /></td>
			</tr>
		</form>

	</table>

	<script src="/farmocean/resources/js/mypage/changeinfo.js?ver=<%=System.currentTimeMillis() %>"></script>
		<script src="/farmocean/resources/js/mypage/changeinfo2.js?ver=<%=System.currentTimeMillis() %>"></script>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		
		
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<title>상품 판매 목록</title>
		<style>
			table { 
				width: 1280px;
			}
			td {
				padding: 20px;
				border: 1px solid #666666;
			}
			th {
				padding: 20px;
				border: 1px solid #666666;
			}
			tr:hover {
					background: rgb(77,77,77);
					color: #fff;
					cursor: pointer
			}
			
			th, td {
  				text-align: center;
			}
		</style>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>

		<h3>내가 올린 상품</h3>
		
				<table id="sellgoods" border='1' style = "word-break: break-all">
		
			<tr>
				<th>번호</th>
				<th>판매 상품</th>
				<th>가격</th>
				<th>판매여부</th>
				<th>판매상품수정</th>
				<th>숨김여부</th>
				<th>상품삭제</th>
			</tr>
			
		</table>

<script src="/farmocean/resources/js/mypage/sellgoods.js?ver=<%=System.currentTimeMillis() %>"></script>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>
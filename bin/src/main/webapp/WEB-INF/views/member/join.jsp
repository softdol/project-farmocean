<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>噺据亜脊</title>
<style>
input{
	width:60%;
}

table{
text-align: center;
}
</style>
<%@ include file="/resources/jspf/header.jspf" %>
</head>

<body>
<%@ include file="/resources/jspf/body_header.jspf" %>


        	<div class="border">
            	<h1>姥古切 噺据亜脊</h1>
            		<table border="1">
            		<tr><td>焼戚巨</td><td><input type="text" id="post_member_id" placeholder="焼戚巨研 脊径背爽室推.">
            		<button id="idCheckBtn">掻差溌昔</button></td></tr>
					<tr><td colspan="2">焼戚巨 薦鉦繕闇 : 慎庚+収切 5~12切軒幻 紫遂亜管杯艦陥<div id="id_out">　</div></td></tr>            		            		
           			<tr><td>搾腔腰硲</td><td><input type="password" id="post_member_pw" placeholder="搾腔腰硲研 脊径背爽室推"></td></tr>
           			<tr><td colspan="2">搾腔腰硲 薦鉦繕闇 : けけけけけけ<div id="pw_out">　</div></td></tr>
           			<tr><td>搾腔腰硲溌昔</td><td><input type="password" id="post_member_pw_check" placeholder="搾腔腰硲研 廃腰 希 脊径背爽室推."></td></tr>
           			<tr><td colspan="2"><div id="pw_out">　</div></td></tr>           			
           			<tr><td>戚硯</td><td><input type="text" id="post_member_name"  placeholder="戚硯 脊径背爽室推."></td></tr>
           			<tr><td>莞革績</td><td><input type="text" id="post_member_nickName"  placeholder="莞革績 脊径背爽室推."></td></tr>
           			<tr><td colspan="2">莞革績 薦鉦繕闇 : 慎庚+収切 5~12切軒幻 紫遂亜管杯艦陥<div id="nickname_out">　</div></td></tr>
					<tr><td>戚五析</td><td><input type="text" id="post_member_email" placeholder="戚五析聖 脊径背爽室推." ></td></tr>
					<tr><td colspan="2">戚五析 薦鉦繕闇 : 慎庚+収切 5~12切軒幻 紫遂亜管杯艦陥<div id="email_out">　</div></td></tr>
					<tr>
						<td>穿鉢腰硲</td>
						<td>
							<input style="width:29%; text-align: center" type="text" id="post_member_phoneNum1" placeholder="穿鉢腰硲研 脊径背爽室推." > -
							<input style="width:29%; text-align: center" type="text" id="post_member_phoneNum2" placeholder="穿鉢腰硲研 脊径背爽室推." > -
							<input style="width:29%; text-align: center" type="text" id="post_member_phoneNum3" placeholder="穿鉢腰硲研 脊径背爽室推." >
						</td>
					</tr>

					</table>

            		<tr><td><div id="out"></div></td><td><button id="join_btn">噺据亜脊</button></td></tr>
	
        	</div> 

    	
	
	<script src="/farmocean/resources/js/member/buyerJoin.js"></script>

	


</body>
</html>


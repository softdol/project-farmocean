<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ������</title>
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
            	<h1>������ ȸ������</h1>
            		<table border="1">
            		<tr><td>���̵�</td><td><input type="text" id="post_member_id" placeholder="���̵� �Է����ּ���.">
            		<button id="idCheckBtn">�ߺ�Ȯ��</button></td></tr>
					<tr><td colspan="2">���̵� �������� : ����+���� 5~12�ڸ��� ��밡���մϴ�<div id="id_out">��</div></td></tr>            		            		
           			<tr><td>��й�ȣ</td><td><input type="password" id="post_member_pw" placeholder="��й�ȣ�� �Է����ּ���"></td></tr>
           			<tr><td colspan="2">��й�ȣ �������� : ������������<div id="pw_out">��</div></td></tr>
           			<tr><td>��й�ȣȮ��</td><td><input type="password" id="post_member_pw_check" placeholder="��й�ȣ�� �ѹ� �� �Է����ּ���."></td></tr>
           			<tr><td colspan="2"><div id="pw_out">��</div></td></tr>           			
           			<tr><td>�̸�</td><td><input type="text" id="post_member_name"  placeholder="�̸� �Է����ּ���."></td></tr>
           			<tr><td>�г���</td><td><input type="text" id="post_member_nickName"  placeholder="�г��� �Է����ּ���."></td></tr>
           			<tr><td colspan="2">�г��� �������� : ����+���� 5~12�ڸ��� ��밡���մϴ�<div id="nickname_out">��</div></td></tr>
					<tr><td>�̸���</td><td><input type="text" id="post_member_email" placeholder="�̸����� �Է����ּ���." ></td></tr>
					<tr><td colspan="2">�̸��� �������� : ����+���� 5~12�ڸ��� ��밡���մϴ�<div id="email_out">��</div></td></tr>
					<tr>
						<td>��ȭ��ȣ</td>
						<td>
							<input style="width:29%; text-align: center" type="text" id="post_member_phoneNum1" placeholder="��ȭ��ȣ�� �Է����ּ���." > -
							<input style="width:29%; text-align: center" type="text" id="post_member_phoneNum2" placeholder="��ȭ��ȣ�� �Է����ּ���." > -
							<input style="width:29%; text-align: center" type="text" id="post_member_phoneNum3" placeholder="��ȭ��ȣ�� �Է����ּ���." >
						</td>
					</tr>

					</table>

            		<tr><td><div id="out"></div></td><td><button id="join_btn">ȸ������</button></td></tr>
	
        	</div> 

    	
	
	<script src="/farmocean/resources/js/member/buyerJoin.js"></script>

	


</body>
</html>

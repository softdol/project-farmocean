<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<script src="<c:url value="/resources/js/follow/list.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/admin/main.css" />" />
<title>sample</title>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>
	
		
			<div id="searchResult">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">��û�� / ��ǰ��</th>
							<th scope="col">�Ǹ���</th>
							<th scope="col">����</th>
							<th scope="col">������ȣ</th>
							<th scope="col">�ּ�</th>
							<th scope="col">����</th>
							<th scope="col">�Ϸ�ó��</th>
						</tr>
					</thead>
				
					<tbody id="tableAdd">		
						
												
						<c:forEach items="${buyList }" var="buyInfo" varStatus="status">
						<tr>
							<td> [${buyInfo.view_regdate }] <br>
								 ${buyInfo.prod_name }</td>
							<td> ${buyInfo.member_nickname }(${buyInfo.sell_id })</td>
							<td> ${buyInfo.view_price }��</td>
							<td> ${buyInfo.post_code }</td>
							<td> ${buyInfo.view_address }</td>
							<td>
								<select name="state${status.index }" id="state${status.index }">
									<option value="0"<c:if test="${buyInfo.state eq 0}"> selected </c:if>>��û</option>
									<option value="1"<c:if test="${buyInfo.state eq 1}"> selected </c:if>>����</option>
									<option value="2"<c:if test="${buyInfo.state eq 2}"> selected </c:if>>�����</option>
									<option value="3"<c:if test="${buyInfo.state eq 3}"> selected </c:if>>���Ȯ��</option>
									<option value="4"<c:if test="${buyInfo.state eq 4}"> selected </c:if>>��ǰ</option>
									<option value="5"<c:if test="${buyInfo.state eq 5}"> selected </c:if>>���</option>
									<option value="10"<c:if test="${buyInfo.state eq 10}"> selected </c:if>>�ǸſϷ�</option>									
								</select>
							</td>
							<td> <button class="btn btn-danger" onclick="fnChgBuyInfo(${buyInfo.buy_idx},'state${status.index}' )">���¼���</button></td>
						</tr>
						</c:forEach>
						
						 		    
					</tbody>
				</table>			
			</div>		
			<nav aria-label="...">
				<ul class="pagination justify-content-center" id="pageNav">					
					
		  		</ul>
			</nav>	
		</div>    
	</div>
</div>
<%@ include file="/resources/jspf/body_footer.jspf" %>
</body>
</html>
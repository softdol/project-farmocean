
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="${path}/resources/css/product/product_detail.css">

<title>��ǰ �� ������(���⿡ ��ǰ �̸� ��)</title>
<%@ include file="/resources/jspf/header.jspf" %>
</head>
<body>
<%@ include file="/resources/jspf/body_header.jspf" %>   
<input id="input-prod-idx" type="hidden" value="${product.prod_idx }"></input>
�Ǹ��� ���̵� : <input id="input-seller-id" type="text" value="${product.member_id}">


    <!-- http://localhost:8888/farmocean/product/detail/2525 -->
	<a href="#"></a>      
    <c:choose>
		<c:when test="${sessionScope.loginId eq null }">
		   �α��� �� �̿� �����մϴ�
		</c:when>
		<c:otherwise>
		   ID : [${sessionScope.loginId.member_id }] 
		   �̸� : [${sessionScope.loginId.member_name}]
		   ��� : [${sessionScope.loginId.member_pw}]
		</c:otherwise>
    </c:choose>



    <a href="#" id="login">�α�</a>
    <a href="#" id="logout">�ΰ���</a>
	
	<a id="test-a" href=""></a>
	
    <div id="prod-detail-container">
		
        <div id="prod-info1" class="prod-detail" >
            <!-- ��ǰ �̹���, �̸�, ����, �Ǹſ���, ��, ���� �ð�(ī��Ʈ�ٿ� ��� �� �� ����)...  -->
            <img id="prod-img" src="${prodImg.img_url}" alt="" />
            <div id="prod-info1-simple">
                <div id="prod-info1-name">${product.prod_name }</div>
                <div id="prod-info1-price">${product.prod_price }��</div>
                <div id="prod-info1-sell-status">${product.prod_sell }</div>
                <a href="#" id="prod-info1-heart">��</a>
                <div id="prod-info1-deadline-timer">���� �ð� 119�� 6�ð� 56�� 8��</div>
                <!-- out.div�� �ϸ� �� �� -->
            </div>
        </div>        
		
        <div id="prod-seller" class="prod-detail">
            <!-- ��ǰ �Ǹ��� ���� -->
           	<img id="seller-img" src="${member.member_image}" alt="" />
           	<table id="seller-table">
           		<tr><td id="seller-nickname" class="seller-td">${member.member_nickName }</td></tr>
           		<tr><td id="seller-phone" class="seller-td">����ó : ${member.member_phoneNum }</td></tr>
           		<tr><td id="seller-account" class="seller-td">���� : ${member.member_accountNum }</td></tr>
           	</table>
           	<a href="#" id="seller-contact">���� ������</a>
        </div>

        <div id="prod-detail-nav" class="prod-detail">
            <a href="#prod-info2" id="prod-detail-nav-prod-info">�� ����</a>
            <a href="#prod-review" id="prod-detail-nav-prod-review">�ı�</a>
            <a href="#prod-comment" id="prod-detail-nav-prod-comment">�ֹ�/����</a>
        </div>

        <div id="prod-info2" class="prod-detail">
            <!-- ��ǰ �� ���� (.innerHTML�� prod_detail �� prod-content �ҷ����� ��)
            <br />+ ���� ���� ���� ���� ����, padding ���� -->
            ${product.prod_info }
        </div>

        <div id="prod-review" class="prod-detail"> <!--flex. column-->
            <!-- ��ǰ �ı�
            <br />�ƿ� Ʋ�� 5�� �������� �ű⿡ �ش��ϴ� ������ �ҷ�����
            <br>���信 ��ϵ� ������ ���� ��� visibility hidden����. ��ϵ� ���� ������ 2�� �̻��� ��� �������� ǥ��
            <br>������� ����� �����̳� ���� ���, ������ ���� hidden, visible. �� �������� +����������� ǥ��(�ٵ� �̰͵� �� �� ���� ������ hidden)
            <br>�׸��� �ı� �ۼ� �������� �����س��� (���� ���) -->
           	<div id="review-write-popup-btn-area"><button id="review-write-popup-btn">���� �ۼ�</button></div>
            <div id="prod-review-picture-container"> <!--flex. row-->
                <div id="prod-review-picture1" class="prod-review-picture"></div>
                <div id="prod-review-picture-more" class="prod-review-picture"></div>
            </div>
            
            <div id="review-container"></div>
            
			<nav aria-label="Page navigation example">
				<ul class="pagination" id="review-pagination-out">
				</ul>
			</nav>
        </div>

        <div id="prod-comment" class="prod-detail"> 
            
            <c:choose>
                <c:when test="${sessionScope.loginId eq null }"></c:when>
                <c:otherwise>
                    <div id="prod-comment-input">
                        <textarea id="prod-comment-textarea"></textarea><button id="prod-comment-input-btn">�Է�</button>
 	                    <div id="comment-secret-div"><input id="comment-secret" type="checkbox"><label for="comment-secret">&nbsp;��б�</label></div>
                    </div>
                </c:otherwise>
            </c:choose>

			<div id="comment-container">
			</div>
            
			<nav aria-label="Page navigation example">
				<ul class="pagination" id="comment-pagination-out">
		
				</ul>
			</nav>
            <div id="no-comment"></div>
        </div>
    </div>



</body>

	<script charset="EUC-KR" src="${path}/resources/js/product/prod_detail.js"></script>

</html>




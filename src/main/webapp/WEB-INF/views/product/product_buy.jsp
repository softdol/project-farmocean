<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%@ include file="/resources/jspf/header.jspf" %>
<script src="<c:url value="/resources/js/product/prod_buy.js" />"></script>
<title>${productTitle}</title>
</head>
<body>
	<c:choose>
    	<c:when test="${sessionScope.loginId eq null || productTitle eq null }">
    		<script type="text/javascript">
    			alert('�α��� ������ ���ų� �߸��� ��ǰ �Դϴ�.');
    			window.close();
    		</script>
    	</c:when>    
    	<c:otherwise>
    	<input type="hidden" id="productId" value="${productId }" />
   			<div class="card" style="width: 18rem;">
				<img src="${productImg.img_url }" class="card-img-top" alt="${productTitle}">
				<div class="card-body">
					<h5 class="card-title">${productTitle}</h5>
					<p class="card-text">[��������]</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">���� : ${productPrice} ��</li>
					<li class="list-group-item">���� �ð� <br /> ${productDeadline}</li>
					<li class="list-group-item">
						����� ���� �Է�<br />
						
						<input type="text" onclick="execDaumPostcode()" id="postcode" placeholder="������ȣ" readonly>
						<input type="button" onclick="execDaumPostcode()" value="������ȣ ã��"><br>
						<input type="text" id="roadAddress" placeholder="���θ��ּ�" readonly>
						<input type="text" id="jibunAddress" placeholder="�����ּ�" readonly>
						<span id="guide" style="color:#999;display:none"></span>
						<input type="text" id="detailAddress" placeholder="���ּ�">
						<input type="text" id="extraAddress" placeholder="�����׸�">
						
					</li>
				</ul>
				<div class="card-body">
					<button onclick="fnBuyProd()" class="btn btn-primary">���� ��û</button>					
					<button onclick="window.close();"  class="btn btn-danger">â �ݱ�</button>					
				</div>
			</div>
			
			<!--  ${productImg } -->
		    ${product}
   		</c:otherwise>	
    </c:choose>	
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    //�� ���������� ���θ� �ּ� ǥ�� ��Ŀ� ���� ���ɿ� ����, �������� �����͸� �����Ͽ� �ùٸ� �ּҸ� �����ϴ� ����� �����մϴ�.
	    function execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.
	
	                // ���θ� �ּ��� ���� ��Ģ�� ���� �ּҸ� ǥ���Ѵ�.
	                // �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
	                var roadAddr = data.roadAddress; // ���θ� �ּ� ����
	                var extraRoadAddr = ''; // ���� �׸� ����
	
	                // ���������� ���� ��� �߰��Ѵ�. (�������� ����)
	                // �������� ��� ������ ���ڰ� "��/��/��"�� ������.
	                if(data.bname !== '' && /[��|��|��]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // �ǹ����� �ְ�, ���������� ��� �߰��Ѵ�.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // ǥ���� �����׸��� ���� ���, ��ȣ���� �߰��� ���� ���ڿ��� �����.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	
	                // ������ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
	                postcode.value = data.zonecode;
	                roadAddress.value = roadAddr;
	                jibunAddress.value = data.jibunAddress;
	                
	                // �����׸� ���ڿ��� ���� ��� �ش� �ʵ忡 �ִ´�.
	                if(roadAddr !== ''){
	                	extraAddress.value = extraRoadAddr;
	                } else {
	                	extraAddress.value = '';
	                }
	
	                var guideTextBox = document.getElementById("guide");
	                // ����ڰ� '���� ����'�� Ŭ���� ���, ���� �ּҶ�� ǥ�ø� ���ش�.
	                if(data.autoRoadAddress) {
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    guideTextBox.innerHTML = '(���� ���θ� �ּ� : ' + expRoadAddr + ')';
	                    guideTextBox.style.display = 'block';
	
	                } else if(data.autoJibunAddress) {
	                    var expJibunAddr = data.autoJibunAddress;
	                    guideTextBox.innerHTML = '(���� ���� �ּ� : ' + expJibunAddr + ')';
	                    guideTextBox.style.display = 'block';
	                } else {
	                    guideTextBox.innerHTML = '';
	                    guideTextBox.style.display = 'none';
	                }
	            }
	        }).open();
	    }
	</script>
</body>
</html>


const editContainer = document.getElementById('edit-container');
const container = document.getElementById('container');

const title = document.getElementById('prod-name');
const content = document.getElementById('editor1'); //근데 ck에디터는 이걸로 value 못 가져옴. 일단 텍스트에리어가 있는지 없는지 체크하는 용으로만 놔둠.
//const contentValue = CKEDITOR.instances.editor1.getData();
const price = document.getElementById('prod-price');
const stock = document.getElementById('prod-stock');
const deadline = document.getElementById('prod-sell-deadline');
const cate = document.getElementById('cate-idx');
const btnContainer = document.getElementById('btn-container');
const previewCont = document.getElementById('preview-cont');

const updateBtn = document.getElementById('update-btn'); 
const frmIns = document.getElementById('frm-ins');
const resetBtn = document.getElementById('reset-btn');


let thumbIdx = 0;

var fileNo = 0;
var filesArr = new Array();
var prodIdx = ''; 

let filePaths = new Array();



if(editContainer != null) {
    CKEDITOR.replace('editor1',
        {
            filebrowserUploadUrl: '/farmocean/prod_detail_write_img_upload/prod_img',
            width : 1000,  // 입력창의 넓이, 넓이는 config.js 에서 % 로 제어
            height : 500,  // 입력창의 높이
            startupFocus : false
        });

    
}


if(deadline != null) {
    //input datetime-local 오늘 날짜 이후로만 선택 가능하게 하기
    deadline.value= new Date().toISOString().slice(0, -1);
    var now_utc = Date.now(); // 지금 날짜를 밀리초로
    // getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
    var timeOff = new Date().getTimezoneOffset()*60000; // 분단위를 밀리초로 변환
    // new Date(today-timeOff).toISOString()은 '2022-09-05T23:17:38.134Z'를 반환
    var today = new Date(now_utc-timeOff).toISOString().substring(0, 16);
    deadline.setAttribute("min", today);
}




function formNullChk() {

    const textVal = CKEDITOR.instances['editor1'].getData();

    if(title != null || content != null || 
        price != null || stock != null ||
        deadline != null || cate != null) {
        console.log('타이틀 : ', title);
        console.log('내용 : ', textVal);
        console.log('가격 : ', price);
        console.log('재고 : ', stock);
        console.log('마감일 : ', deadline); 
        console.log('카테 : ', cate);

        if(title.value.length < 1 || textVal.length < 1 ||
            price.value.length < 1 || stock.value.length < 1 || 
            deadline.value.length < 1 || cate.value.length < 1) {
            console.log('타이틀 값 : ', title.value);
            console.log('내용 값 : ',textVal); //if문에서 contentValue.length < 1 || 일단 뺌
            console.log('가격 값 : ',price.value);
            console.log('재고 값 : ',stock.value);
            console.log('마감일 값 : ',deadline.value);
            console.log('카테 값 : ',cate.value);

            return true;
        }
        return false;        
    }
};

if(stock != null ) {
    stock.onkeydown = function(e) {
        if(!((e.keyCode > 95 && e.keyCode < 106)
            || (e.keyCode > 47 && e.keyCode < 58) 
            || e.keyCode == 8)) {
            return false;
        }
    }    
}
if(price != null ) {
    price.onkeydown = function(e) {
        if(!((e.keyCode > 95 && e.keyCode < 106)
            || (e.keyCode > 47 && e.keyCode < 58) 
            || e.keyCode == 8)) {
            return false;
        }
    }    
}





function date_to_str(format){
    // Sat Nov 26 2022 05:10:27 GMT+0900 -> yyyy-MM-ddThh:mm:ss or yyyy-MM-ddThh:mm:ss.SSS
    var year = format.getFullYear();
    var month = format.getMonth() + 1;
    if(month<10) month = '0' + month;
    var date = format.getDate();
    if(date<10) date = '0' + date;
    var hour = format.getHours();
    if(hour<10) hour = '0' + hour;
    var min = format.getMinutes();
    if(min<10) min = '0' + min;
    var sec = format.getSeconds();
    if(sec<10) sec = '0' + sec;
    return year + "-" + month + "-" + date + "T" + hour + ":" + min + ":" + sec;
}



if(updateBtn != null) {
    updateBtn.addEventListener('click', (e)=> {
	    
        if(formNullChk()) {
            alert('비어 있는 항목을 모두 입력해주세요.');		
        } else {
	        let additionalInput = document.createElement('input');
	        additionalInput.setAttribute('name', 'prod_idx');      //name
			additionalInput.setAttribute('value', prodIdx);        //value			
			frmIns.appendChild(additionalInput);                 //from			        
            frmIns.submit();		
        }
        formNullChk();
    });
}



window.addEventListener('load',() => {
	
	//현재 창의 주소값 맨 뒤에 붙은 prod_idx 구하기
    var path = window.location.pathname;
	// /farmocean/product/product_detail_edit/{prod_idx}
    prodIdx = path.replace('/farmocean/product/product_detail_edit/', '');
            
    const xhttp2 = new XMLHttpRequest();
    xhttp2.open('Get', '/farmocean/authentication_seller/' + prodIdx);
    xhttp2.send();
    xhttp2.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;
        if(readyState == 4) {
            const result = e.target.responseText;
            if(result != 1) {
                alert('판매자 본인만 수정할 수 있습니다.');
                editContainer.remove();
                container.innerHTML = '판매자 본인만 수정할 수 있습니다.';
            }
        }
    });



    const xhttp1 = new XMLHttpRequest();
    xhttp1.open('Get', '/farmocean/prod/get_product/' + prodIdx);
    xhttp1.send();
    xhttp1.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;

        if(readyState == 4) {
            const responseText = e.target.responseText;
            const product = JSON.parse(responseText);
            
            if(title != null || content != null || 
                price != null || stock != null ||
                deadline != null || cate != null) {
                    title.value = product.prod_name;
                    CKEDITOR.instances.editor1.setData(product.prod_info);
                    price.value = product.prod_price;
                    stock.value = product.prod_stock;
                    $("#cate").val(product.cate_idx).prop("selected", true);
                    deadline.value = date_to_str(new Date(product.prod_sell_deadline));
            }
        }
    });

    const xhttp15 = new XMLHttpRequest();
    xhttp15.open('GET', '/farmocean/prod/get_product_img/' + prodIdx);
    xhttp15.send();
    xhttp15.addEventListener('readystatechange', (e)=> {
        const readyState = e.target.readyState;
        if(readyState == 4) {
            
            const responseText = e.target.responseText;
            const imgList = JSON.parse(responseText);
            for(let i = 0; i < imgList.length; ++i) {
                previewCont.innerHTML +=
                                            `<div class="img-cont" id="img-cont` + (fileNo) + `">
                                                <label for="radio` + (fileNo) + `">
                                                    <img class="img" id="img` + (fileNo) + `" data-fileNo="` + (fileNo) + `" src="`+ imgList[i].img_url +`" alt="" onclick="thumb(` + (fileNo) + `);"/>	
                                                </label>														
                                                <input type="radio" id="radio` + (fileNo) + `" class="thumb-radio" name="thumbnail" value="` + (fileNo) + `"/>
                                                <button class="img-delete" onclick="deleteFile(` + (fileNo) + `);">삭제</button>
                                            </div>`;
            }

        }
    });



    

});






/* 첨부파일 추가 */
function addFile(obj){
    var maxFileCnt = 5;   // 첨부파일 최대 개수
    var attFileCnt = document.querySelectorAll('.img-cont').length;    // 기존 추가된 첨부파일 개수
    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수(파일 선택창에서 ctrl 로 파일 여러개 선택 가능)

    // 첨부파일 개수 확인
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + " 개까지 첨부 가능합니다.");
    }

    for (var i = 0; i < Math.min(curFileCnt, remainFileCnt); i++) { //선택한 파일이 4개고 추가로 첨부 가능한 파일 개수가 1개면 1개만 업로드 됨
		
        const file = obj.files[i];
		var imgPreview;
        // 첨부파일 검증
        if (validation(file)) {
            // 파일 배열에 담기
            var reader = new FileReader();
            reader.onload = function (e) {    
			
			previewCont.innerHTML +=
										`<div class="img-cont" id="img-cont` + (fileNo) + `">
											<label for="radio` + (fileNo) + `">
												<img class="img" id="img` + (fileNo) + `" data-fileNo="` + (fileNo) + `" src="`+ e.target.result +`" alt="" onclick="thumb(` + (fileNo) + `);"/>	
											</label>														
											<input type="radio" id="radio` + (fileNo) + `" class="thumb-radio" name="thumbnail" value="` + (fileNo) + `"/>
											<button class="img-delete" onclick="deleteFile(` + (fileNo) + `);">삭제</button>
										</div>`;
			filesArr.push(file);
            };
            reader.readAsDataURL(file);
			
            // 목록 추가
            // let htmlData = '';
            // htmlData += '<div id="file' + fileNo + '" class="filebox">';
            // console.log('파일명div id : file', fileNo);
            // htmlData += '   <p class="name">' + file.name + '</p>';
            // htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');">삭제</a>';
            // htmlData += '</div>';
            // $('#file-list').append(htmlData);
            fileNo++;
        } else {
            continue;
        }
    }
    // 초기화
    document.querySelector("input[type=file]").value = "";
}


/* 첨부파일 검증 */
function validation(obj){
    const fileTypes = ['image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > (7 * 1024 * 1024)) {
        alert("최대 파일 용량인 7MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("이미지 파일만 첨부할 수 있습니다.");
        return false;
    } else {
        return true;
    }
}


/* 첨부파일 삭제 */
function deleteFile(num) {
	console.log('delete file(num) : ' , num);
    document.querySelector("#img-cont" + num).remove();
	filesArr[num-1].is_delete = true;

}






// 이미지 클릭 시 클릭된 이미지만 테두리 적용
function thumb(num) {
	const imgList = document.getElementsByClassName('img');
	for(let i = 0; i < imgList.length; ++i) {
		imgList[i].style.border = 'none';
	}
	document.getElementById('img' + num).style.border = '3px solid rgb(96, 152, 255)';
    thumbIdx = num-1;
}

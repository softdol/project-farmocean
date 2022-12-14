
// 닉네임 중복확인 또는 형식 확인------------------------------------------------------------------------

const nickNameField = document.getElementById('nickname'); // 닉네임 작성한 곳
const nickNameChecker = document.getElementById('checkNickBtn'); // 닉네임 중복 확인 버튼
const out = document.getElementById('out'); // 닉네임 중복 확인 표시

const xhttp = new XMLHttpRequest();
const xhttp1 = new XMLHttpRequest();

xhttp.open('GET','/farmocean/memberUpdate/listAll');
xhttp.send();

nickNameChecker.addEventListener('click',(e)=>{
			
	if (xhttp.readyState == 4) {
		if (xhttp.status == 200) {
			
			const member = JSON.parse(xhttp.responseText);
            const memberNickNames = new Array();
            
            for (i = 0; i < member.length; ++i) {
            	memberNickNames[i] = member[i].member_nickName;
            }
            
             if (memberNickNames.includes(nickNameField.value)) {
                alert("이미 존재하는 닉네임 입니다.")
                out.innerText = "이미 존재하는 닉네임 입니다.";
                out.style.color = "red"

                nickNameField.focus();
            } else {
            
                var result = confirm("사용 가능한 닉네임 입니다. 사용하시겠습니까?");
        
                if(result) {
                    out.innerText = "사용 가능한 닉네임 입니다. 중복확인이 완료되었습니다.";
                    out.style.color = "green"
            	
                    // readOnly로 바꿔주는 방법
                    nickNameField.readOnly = true;
                }
                else {
                    out.innerText = "사용 가능한 닉네임 입니다. 중복확인을 눌러주세요.";
                    out.style.color = "green"
                }
                	
            }
            
		}
	}
			
});

nickNameField.addEventListener('keyup',(e)=>{
    if (nickNameField.value == '') {
        out.innerText = "닉네임이 비어있습니다.";
        out.style.color = "red"

        nickNameField.focus();
    } else {
        
        xhttp1.open('GET','/farmocean/memberUpdate/checkNickname/' + nickNameField.value);
        xhttp1.send();
        
        xhttp1.addEventListener('readystatechange', (e) => {
        
        const readyState = e.target.readyState;
        
            if (readyState == 4) {
                const responseText = e.target.responseText;
            
                if (responseText == 2) {
                    out.innerText = "3자 이상 10자 이하, 영어 또는 숫자 또는 한글로 구성되어야 합니다.";
                    out.style.color = "red";
                
                    nickNameField.focus();
                } else {
                    out.innerText = "사용 가능합니다. 중복확인을 눌러주세요.";
                    out.style.color = "green";
                    
                }
            
            }
        });
    }
})


// 이메일 형식 확인---------------------------------------------------------------------------------

const email = document.getElementById('email'); // 이메일 작성한 곳
const out2 = document.getElementById('out2'); // 이메일 중복 확인 표시

const xhttp3 = new XMLHttpRequest();

email.addEventListener('keyup',(e)=>{
    if (email.value == '') {
        out2.innerText = "이메일이 비어있습니다";
        out2.style.color = "red";

        email.focus();
    } else {
        
        xhttp3.open('GET','/farmocean/memberUpdate/checkEmail/' + email.value);
	    xhttp3.send();
        
        xhttp3.addEventListener('readystatechange', (e) => {
        
        const readyState = e.target.readyState;
        
            if (readyState == 4) {
                const responseText = e.target.responseText;

                console.log("responseText: " + responseText);
            
                if (responseText == 2) {
                    out2.innerText = "이메일의 구성이 잘못되었습니다.";
                    out2.style.color = "red";
                
                    email.focus();
                } else {
                    out2.innerText = "사용 가능한 이메일 입니다.";
                    out2.style.color = "green";
                }
            
            }
        });
    }
})


// 서브밋 버튼--------------------------------------------------------------------------------------

const subBtn = document.getElementById('subBtn'); // 서브밋 버튼

const member_address = document.getElementById('member_address'); // 현재 주소
		
// 선택한 주소
const postcode = document.getElementById('sample6_postcode');
const address = document.getElementById("sample6_address");
const extraAddress = document.getElementById("sample6_extraAddress");
const detailAddress = document.getElementById("sample6_detailAddress");

subBtn.addEventListener('click', (e) => {
    const check = out.innerHTML; //닉네임 중복 확인 표시
    const check2 = out2.innerHTML; // 이메일 중복 확인 표시

    console.log("check: " + check);
    console.log("check2: " + check2);


    if (!(check == '사용 가능한 닉네임 입니다. 중복확인이 완료되었습니다.' || check == '&nbsp;')) {
            alert('닉네임 중복확인을 완료 해주세요');
            // 이벤트 중단
            e.preventDefault();
        } else if (!(check2 == '사용 가능한 이메일 입니다.' || check2 == '&nbsp;')) {
            alert('이메일을 확인 해주세요');
            // 이벤트 중단
            e.preventDefault();
        } else {
    
            if (postcode.value == '' || address.value == '' || detailAddress.value  ==  '' ) {
    
            } else {
                if (extraAddress == '') {
                    member_address.value = '[' + postcode.value + ']' + address.value + detailAddress.value;
                } else {
                    member_address.value = '[' + postcode.value + ']' + address.value + ' ' + extraAddress.value + ' ' + detailAddress.value;
                }
            }

        }
});

function emulAcceptCharset(form) {
    if (form.canHaveHTML) {
        document.charset = form.acceptCharset;
    }

    return true;
}


function OpenWin() {
    var f = document.cplogn;

    shape = 'width=520,height=650,';
    shape += 'left=70,top=10,toolbar=no,location=no,directories=no,status=yes,';
    shape += 'menubar=yes,scrollbars=no,resizable=yes';

    var win = open('', 'MC', shape);

    f.target = 'MC';

    emulAcceptCharset(f);

    f.submit();

    if (win.focus) {
        win.focus();
    }
}
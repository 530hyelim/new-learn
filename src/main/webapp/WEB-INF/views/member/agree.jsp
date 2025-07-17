<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 - 약관동의</title>

<%-- CSS 파일 경로로 바꿔줘야함 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/agree.css">
</head>
<body>

    <h1 class="page-title">회원가입</h1>

    <div class="progress-indicator">
        <div class="step active">
            <div class="step-circle"></div>
            <div class="step-label">약관동의</div>
        </div>
        <div class="step-line"></div>
        <div class="step">
            <div class="step-circle"></div>
            <div class="step-label">정보입력</div>
        </div>
        <div class="step-line"></div>
        <div class="step">
            <div class="step-circle"></div>
            <div class="step-label">가입완료</div>
        </div>
    </div>

    <div class="agree-container">
        <h2 class="logo">new Learn();</h2>
        
        <form id="agreeForm" action="${pageContext.request.contextPath}/member/enrollForm" method="get">
            
            <!-- (필수) 이용약관 -->
            <div class="terms-section">
                <div class="terms-box">
                    <p class="terms-title">(필수) 이용약관</p>
                    <p>여러분을 환영합니다. new Learn(이하 '뉴런'이라 함) 이용에 앞서서 감사드립니다. 본 약관은 뉴런의 다양한 서비스를 이용하는 여러분과 뉴런이 서비스를 이용함에 있어 필요한 권리, 의무 및 책임사항, 이용조건 및 절차 등 기본적인 사항을 규정하고 있으므로, 조금만 시간을 내서 주의 깊게 읽어주시기 바랍니다...</p>
                </div>
                <div class="terms-footer">
                    <label>
                        이용약관에 동의합니다.
                        <input type="checkbox" name="terms" class="required-agree">
                    </label>
                </div>
            </div>

            <!-- (필수) 개인정보 수집 및 이용 -->
            <div class="terms-section">
                <div class="terms-box">
                    <p class="terms-title">(필수) 개인정보 수집 및 이용</p>
                    <p>개인정보처리방침은 다음과 같은 중요한 의미를 가지고 있습니다. 뉴런이 어떤 정보를 수집하고, 수집한 정보를 어떻게 사용하며, 필요에 따라 누구와 이를 공유('위탁 또는 제공')하며, 이용이 끝난 정보를 언제, 어떻게 파기하는지 등 '개인정보' 전반에 걸친 사항을 투명하게 알려드립니다...</p>
                </div>
                <div class="terms-footer">
                    <label>
                        개인정보 수집 및 이용에 동의합니다.
                        <input type="checkbox" name="terms" class="required-agree">
                    </label>
                </div>
            </div>

            <!-- (선택) 마케팅 정보 수신 -->
            <div class="terms-section">
                <div class="terms-box">
                    <p class="terms-title">(선택) 마케팅 정보 수신</p>
                    <p>뉴런은 여러분의 사전 동의를 받은 경우에 한하여, 여러분이 서비스 이용 과정에서 제공한 개인정보를 활용하여 마케팅 및 프로모션 목적으로 문자, 이메일, 푸시 알림 등을 통해 다양한 정보를 제공할 수 있습니다. 본 동의는 선택 사항이며, 동의하지 않으셔도 서비스의 기본 기능 이용에는 아무런 제약이 없습니다.</p>
                </div>
                <div class="terms-footer">
                    <label>
                        마케팅 정보 수신에 동의합니다.
                        <input type="checkbox" name="terms" class="optional-agree">
                    </label>
                </div>
            </div>

            <!-- 하단 전체 동의 및 다음 버튼 -->
            <div class="container-footer">
                <label class="agree-all-label">
                    <input type="checkbox" id="agreeAll">
                    모든 약관에 동의합니다.
                </label>
                <button type="submit" id="nextBtn" disabled>다음</button>
            </div>
        </form>
    </div>

<script>
document.addEventListener('DOMContentLoaded', function(){
    const agreeAll = document.getElementById('agreeAll');
    const requiredCheckboxes = document.querySelectorAll('.required-agree');
    const allCheckboxes = document.querySelectorAll('input[name="terms"]');
    const nextBtn = document.getElementById('nextBtn');

    function updateNextButton() {
        const allRequiredChecked = Array.from(requiredCheckboxes).every(checkbox => checkbox.checked);
        nextBtn.disabled = !allRequiredChecked;
    }

    agreeAll.addEventListener('change', function(){
        allCheckboxes.forEach(checkbox => {
            checkbox.checked = this.checked;
        });
        updateNextButton();
    });

    allCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function(){
            const allChecked = Array.from(allCheckboxes).every(cb => cb.checked);
            agreeAll.checked = allChecked;
            updateNextButton();
        });
    });

    updateNextButton();
});

</script>

</body>
</html>
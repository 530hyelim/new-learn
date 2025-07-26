$(document).ready(function () {
    // ============================
    // 0. 초기 모달 숨김 설정 및 DOM 로드 후 초기화
    // ============================
    $('#createroom_modal').hide();
    $('#friend_profile_modal').hide();
    $('#together_class').hide();
    $('#chattingroomset_controller').hide();
    $('#setroommodal_creater').hide(); // 방장 모달
    $('#setroommodal_member').hide();   // 멤버 모달
    $('#reportroommodal').hide();

    // 비밀번호 그룹 초기 상태 (공개일 때 숨김)
    $('#chatPwGroup').hide(); 
    $('#inputChatPw').prop('required', false); // 초기 필수 속성 제거
    $("#radioPublicY").prop("checked", true); // 공개 라디오 버튼 기본 선택 (JSP에서도 checked 속성 주는 것이 좋음)

    // ============================
    // 1. 채팅방 만들기 모달 관련
    // ============================

    // '채팅방 만들기' 버튼 클릭 시 createroom_modal 열기
    // JSP에서 <button>에 id="createRoomModalBtn"을 추가하는 것이 가장 좋지만,
    // 현재는 class나 nth-child 선택자를 사용하여 friend_top div 안의 첫 번째(유일한) button을 선택합니다.
    $('.friend_top button').on('click', function (event) {
        event.stopPropagation(); // 이벤트 버블링 방지
        $('#createroom_modal').fadeIn();
        // 모달 열 때 폼 초기화 및 초기 상태 설정
        $("#createRoomForm")[0].reset(); 
        $("#chatPwGroup").hide(); 
        $("#radioPublicY").prop("checked", true); // 공개 라디오 버튼 기본 선택
        $('input[name="selectFriendList"]').prop('checked', false); // 친구 선택 초기화
        $('#inputChatPw').prop('required', false); // 비밀번호 필드 필수 속성 제거 (초기 상태)
    });

    // #createroom_modal 의 '취소' 버튼 클릭 시 모달 닫기
    $('#createroom_modal .go_back input:nth-child(1)').on('click', function () {
        $('#createroom_modal').fadeOut();
        // 모달 닫을 때 폼 필드 초기화
        $("#createRoomForm")[0].reset();
        $("#chatPwGroup").hide();
        $("#radioPublicY").prop("checked", true);
        $('input[name="selectFriendList"]').prop("checked", false);
        $('#inputChatPw').prop('required', false);
    });

    // 공개/비공개 라디오 버튼 변경 시 비밀번호 입력창 표시/숨김
    $('input[name="chatPublic"]').change(function() {
        if ($(this).val() === 'N') { // 비공개 선택 시
            $('#chatPwGroup').show();
            $('#inputChatPw').prop('required', true); // 비밀번호 필드를 필수로 만듦
        } else { // 공개 선택 시
            $('#chatPwGroup').hide();
            $('#inputChatPw').val(''); // 비밀번호 초기화
            $('#inputChatPw').prop('required', false); // 필수 속성 제거
        }
    });

    // '확인' 버튼 클릭 (AJAX 제출)
    $("#createRoomBtn").on("click", function(){
        // 유효성 검사
        var chatTitle = $("#inputChatTitle").val().trim();
        if(chatTitle === ''){
            alert("채팅방 제목을 입력해주세요.");
            $("#inputChatTitle").focus();
            return;
        }

        var chatPublic = $("input[name='chatPublic']:checked").val();
        if(chatPublic === 'N'){
            var chatPw = $("#inputChatPw").val().trim();
            if(chatPw === ''){
                alert("비공개 채팅방 비밀번호를 입력해주세요.");
                $("#inputChatPw").focus();
                return;
            }
        }

        var selectedFriends = $("input[name='selectFriendList']:checked").length;
        if(selectedFriends === 0){
            alert("한 명 이상의 대화상대를 초대해주세요.");
            return;
        }

        // 폼 데이터 가져오기 (유효성 검사 통과 후)
        var formData = $("#createRoomForm").serialize();

        // AJAX 요청
        $.ajax({
            url: chatAppContextPath+"/chat/createRoom",
            type: "POST",
            data: formData,
            dataType: "json", // **오타 수정됨: dateType -> dataType**
            success: function(response){
                if(response.success){
                    alert(response.message);
                    $("#createroom_modal").fadeOut(); // 모달 닫기
                    $("#createRoomForm")[0].reset(); // 폼 필드 초기화
                    $("#chatPwGroup").hide(); // 비밀번호 필드 숨김
                    $("#radioPublicY").prop("checked",true); // 공개 라디오 버튼 선택
                    $("input[name='selectFriendList']").prop("checked",false); // 친구 선택 초기화
                    
                    refreshChatRoomList(); // 채팅방 목록 업데이트 호출
                } else {
                    alert("채팅방 생성 실패 : " + response.message);
                }
            },
            error: function(xhr, status, error){
                console.error("AJAX Error : ", status, error, xhr.responseText); // xhr.responseText 추가하여 상세 응답 확인
                var errorMessage = "알 수 없는 오류가 발생했습니다.";
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMessage = xhr.responseJSON.message;
                } else if (xhr.responseText) {
                    errorMessage += "\n서버 응답: " + xhr.responseText;
                }
                alert("채팅방 생성 중 오류가 발생했습니다. : " + errorMessage);
            }
        });
    });

    // ============================
    // 2. 채팅방 목록 동적 업데이트 함수
    // ============================
    function refreshChatRoomList(){
        // TODO: **중요!** 이 loginUserNo 변수는 JSP 파일 내 <script> 태그에서
        // var loginUserNo = ${sessionScope.loginMember.userNo}; 와 같이 선언되어야 합니다.
        // 현재는 JSP에 해당 선언이 없으므로, 임시로 하드코딩된 값을 사용합니다.
        // 실제 배포 시에는 반드시 수정되어야 합니다.
        var loginUserNo = 3; 
        
        // loginUserNo가 JSP에서 문자열로 넘어올 경우를 대비하여 숫자로 변환
        if (typeof loginUserNo === 'string' && !isNaN(parseInt(loginUserNo))) {
            loginUserNo = parseInt(loginUserNo);
        }

        $.ajax({
            url: "/chat/main", // **URL 수정됨: /chat/ChattringRoomListRefresh -> /chat/main**
            type: "GET",
            data: { userNo: loginUserNo }, // 사용자 번호 전달
            dataType: "html", // HTML 응답을 기대
            success: function(html){
                // 받은 전체 HTML에서 id="mainChattingRoomList"인 요소의 내용을 추출하여 현재 페이지에 삽입
                var newChatRoomListContent = $(html).find("#mainChattingRoomList").html();
                $("#mainChattingRoomList").html(newChatRoomListContent);
                console.log("채팅방 목록 업데이트 완료");
            },
            error: function(xhr, status, error){
                console.error("채팅방 목록 업데이트 실패 : ", status, error, xhr.responseText);
                alert("채팅방 목록을 불러오는데 실패했습니다.");
            }
        });
    }

    // ============================
    // 3. 친구 프로필 모달 (이벤트 위임 사용)
    // ============================
    // .friend_mid는 정적 요소이므로, 그 안에 동적으로 생성될 수 있는 .friend_inf에 대해 이벤트 위임
    $('.friend_mid').on('click', '.friend_inf', function (event) {
        event.stopPropagation();
        $('#friend_profile_modal').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px',
        }).fadeIn();
        // TODO: 여기에 해당 친구의 정보를 AJAX로 가져와서 모달에 채우는 로직 추가
    });

    $('#friend_profile_modal .back input').on('click', function () {
        $('#friend_profile_modal').fadeOut();
    });

    // ============================
    // 4. 함께하는 클래스 모달 (이벤트 위임 사용)
    // ============================
    // #friend_profile_modal 내부에 있는 .option1 button 클릭 시
    $('#friend_profile_modal').on('click', '.option1 button', function (event) {
        event.stopPropagation();
        $('#together_class').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px'
        }).fadeIn();
    });

    // ============================
    // 5. 채팅방 설정 컨트롤러 모달 (이벤트 위임 사용)
    // ============================
    // .chattingroomlist_mid는 정적 요소이므로, 그 안에 동적으로 생성될 수 있는 .room_set_button에 대해 이벤트 위임
    $('.chattingroomlist_mid').on('click', 'li .room_set_button', function (event) {
        event.stopPropagation();
        $('#chattingroomset_controller').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px'
        }).fadeIn();
        // TODO: 여기에 해당 채팅방의 정보 (예: 방장 여부)를 가져와서 모달 내용을 구성하는 로직 추가
    });


    // ============================
    // 6. 방장/멤버 설정 모달 (이벤트 위임 사용)
    // ============================
    // #chattingroomset_controller는 정적 요소
    $('#chattingroomset_controller').on('click', '.chattingroomset', function () {
        // 실제 방장인지 아닌지에 따라 모달을 선택적으로 띄워야 합니다.
        // 이 부분은 서버에서 받아온 채팅방 정보에 따라 조건부로 처리해야 합니다.
        // 예를 들어, var isCreater = true; // 서버 응답에서 받아온 값
        // if (isCreater) {
        //     $('#setroommodal_creater').fadeIn();
        // } else {
            $('#setroommodal_member').fadeIn(); // 임시로 멤버 모달만 띄움
        // }
    });

    $('#setroommodal_member .go_back input:nth-child(1)').on('click', function () {
        $('#setroommodal_member').fadeOut();
    });

    // 방장 모달이 활성화되어 있다면, 해당 닫기 버튼도 필요
    $('#setroommodal_creater .go_back input:nth-child(1)').on('click', function () {
        $('#setroommodal_creater').fadeOut();
    });


    // ============================
    // 7. 채팅방 신고 모달 (이벤트 위임 사용)
    // ============================
    // #chattingroomset_controller는 정적 요소
    $('#chattingroomset_controller').on('click', '.chattingroomreport', function () {
        $('#reportroommodal').fadeIn();
    });

    $('#reportroommodal .go_back input:nth-child(1)').on('click', function () {
        $('#reportroommodal').fadeOut();
    });

    // ============================
    // 8. 문서 클릭 시 모달 닫기 공통 로직
    // ============================
    // 여러 모달에 대한 닫기 로직을 한 번에 처리
    $(document).on('click', function (event) {
        // 클릭된 요소가 모달 영역 내부 또는 모달을 여는 버튼/컨트롤러의 자식이 아닌 경우에만 닫기
        const modals = [
            '#createroom_modal',
            '#friend_profile_modal',
            '#together_class',
            '#chattingroomset_controller',
            '#setroommodal_creater',
            '#setroommodal_member',
            '#reportroommodal'
        ];

        let clickedInsideModal = false;
        for (let i = 0; i < modals.length; i++) {
            if ($(event.target).closest(modals[i]).length) {
                clickedInsideModal = true;
                break;
            }
        }

        // 모달을 여는 버튼/컨트롤러 클릭은 모달을 닫지 않도록 예외 처리
        const openTriggers = [
            '.friend_top button', // 채팅방 만들기 버튼
            '.friend_mid .friend_inf', // 친구 프로필 모달 트리거
            '#friend_profile_modal .option1 button', // 함께하는 클래스 트리거
            '.chattingroomlist_mid li .room_set_button', // 채팅방 설정 컨트롤러 트리거
            '#chattingroomset_controller .chattingroomset', // 설정 모달 트리거
            '#chattingroomset_controller .chattingroomreport' // 신고 모달 트리거
        ];

        let clickedOnTrigger = false;
        for (let i = 0; i < openTriggers.length; i++) {
            if ($(event.target).closest(openTriggers[i]).length) {
                clickedOnTrigger = true;
                break;
            }
        }

        if (!clickedInsideModal && !clickedOnTrigger) {
            // 모든 모달 닫기
            $('#createroom_modal').fadeOut();
            $('#friend_profile_modal').fadeOut();
            $('#together_class').fadeOut();
            $('#chattingroomset_controller').fadeOut();
            $('#setroommodal_creater').fadeOut();
            $('#setroommodal_member').fadeOut();
            $('#reportroommodal').fadeOut();
        }
    });

}); // End of $(document).ready()
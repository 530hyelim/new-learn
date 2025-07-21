// /*
//     채팅방 만들기
// */
$(document).ready(function () {
    $('#createroom_modal').hide();

    $('.friend_top button').on('click', function () {
        $('#createroom_modal').fadeIn(); // 이벤트 버블링 방지
    });

    $('#createroom_modal .go_back input:nth-child(1)').on('click', function () {
        $('#createroom_modal').fadeOut();
    });
});

// /*
//     프로필 모달
// */
$(document).ready(function () {
    $('#friend_profile_modal').hide();

    $('.friend_inf').on('click', function (event) {
        event.stopPropagation();
        $('#friend_profile_modal').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px',
        }).fadeIn();
    });

    $('#friend_profile_modal .back input').on('click', function () {
        $('#friend_profile_modal').fadeOut();
    });
});

// /*
//     함께하는 클래스
// */
$(document).ready(function () {
    $('#together_class').hide();

    $('#friend_profile_modal .option1 input').on('click', function (event) {
        event.stopPropagation(); // 모달 뜬 후 바로 사라지는 거 방지
        $('#together_class').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px'
        }).fadeIn();
    });

    // 문서 아무 곳이나 클릭해서 모달 닫기
    $(document).on('click', function () {
        $('#together_class').fadeOut();
    });

    // 모달 내부 클릭 시에는 닫히지 않게
    $('#together_class').on('click', function (event) {
        event.stopPropagation();
    });
});

/*
    채팅방 설정
*/
/*
    채팅방 설정 컨트롤러
*/
$(document).ready(function () {
    $('#chattingroomset-controller').hide();

    $('.chattingroomlist_mid li .room_set_button').on('click', function (event) {
        event.stopPropagation(); // 모달 뜬 후 바로 사라지는 거 방지
        $('#chattingroomset_controller').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px'
        }).fadeIn();
    });

    // 문서 아무 곳이나 클릭해서 모달 닫기
    $(document).on('click', function () {
        $('#chattingroomset_controller').fadeOut();
    });

    // 모달 내부 클릭 시에는 닫히지 않게
    $('#chattingroomset_controller').on('click', function (event) {
        event.stopPropagation();
    });
});
/*
    1. 방장
*/
// $(document).ready(function () {
//     // 모달 숨김
//     $('#setroommodal_creater').hide();

//     // 버튼 클릭 시 모달 보이기
//     $('#chattingroomset_controller .chattingroomset').on('click', function () {
//         $('#setroommodal_creater').fadeIn();
//     });

//     // 취소 버튼
//     $('#setroommodal_creater .go_back input:nth-child(1)').on('click', function () {
//         $('#setroommodal_creater').fadeOut();
//     });
// });

/*
    2. 방장x
*/
$(document).ready(function () {
    $('#setroommodal_member').hide();

    $('#chattingroomset_controller .chattingroomset').on('click', function () {
        $('#setroommodal_member').fadeIn();
    });

    $('#setroommodal_member .go_back input:nth-child(1)').on('click', function () {
        $('#setroommodal_member').fadeOut();
    });
});

/*
    채팅방 신고 모달
*/
$(document).ready(function () {
    $('#reportroommodal').hide();

    $('#chattingroomset_controller .chattingroomreport').on('click', function () {
        $('#reportroommodal').fadeIn();
    });

    $('#reportroommodal .go_back input:nth-child(1)').on('click', function () {
        $('#reportroommodal').fadeOut();
    });
});
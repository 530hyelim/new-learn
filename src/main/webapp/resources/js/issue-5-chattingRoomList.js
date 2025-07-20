/*
    채팅방 만들기
*/
$(document).ready(function () {
    $('#createRoomModal').hide();

    $('.friend-top button').on('click', function () {
        $('#createRoomModal').fadeIn(); // 이벤트 버블링 방지
    });

    $('#createRoomModal .goback input:nth-child(1)').on('click', function () {
        $('#createRoomModal').fadeOut();
    });
});

/*
    프로필 모달
*/
$(document).ready(function () {
    $('#simpleProfileModal').hide();

    $('.friend-inf').on('click', function (event) {
        event.stopPropagation();
        $('#simpleProfileModal').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px',
        }).fadeIn();
    });

    $('#simpleProfileModal .back input').on('click', function () {
        $('#simpleProfileModal').fadeOut();
    });
});

/*
    함께하는 클래스
*/
$(document).ready(function () {
    $('#together-class').hide();

    $('#simpleProfileModal .option1 input').on('click', function (event) {
        event.stopPropagation(); // 모달 뜬 후 바로 사라지는 거 방지
        $('#together-class').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px'
        }).fadeIn();
    });

    // 문서 아무 곳이나 클릭해서 모달 닫기
    $(document).on('click', function () {
        $('#together-class').fadeOut();
    });

    // 모달 내부 클릭 시에는 닫히지 않게
    $('#together-class').on('click', function (event) {
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
    $('#chattingRoomSet-controller').hide();

    $('.chattingRoomList-mid li .room-set').on('click', function (event) {
        event.stopPropagation(); // 모달 뜬 후 바로 사라지는 거 방지
        $('#chattingRoomSet-controller').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px'
        }).fadeIn();
    });

    // 문서 아무 곳이나 클릭해서 모달 닫기
    $(document).on('click', function () {
        $('#chattingRoomSet-controller').fadeOut();
    });

    // 모달 내부 클릭 시에는 닫히지 않게
    $('#chattingRoomSet-controller').on('click', function (event) {
        event.stopPropagation();
    });
});
/*
    1. 방장
*/
// $(document).ready(function () {
//     // 모달 숨김
//     $('#setRoomModal-creater').hide();

//     // 버튼 클릭 시 모달 보이기
//     $('#chattingRoomSet-controller .chattingRoomSet').on('click', function () {
//         $('#setRoomModal-creater').fadeIn();
//     });

//     // 취소 버튼
//     $('#setRoomModal-creater .goback input:nth-child(1)').on('click', function () {
//         $('#setRoomModal-creater').fadeOut();
//     });
// });

/*
    2. 방장x
*/
$(document).ready(function () {
    $('#setRoomModal-member').hide();

    $('#chattingRoomSet-controller .chattingRoomSet').on('click', function () {
        $('#setRoomModal-member').fadeIn();
    });

    $('#setRoomModal-member .goback input:nth-child(1)').on('click', function () {
        $('#setRoomModal-member').fadeOut();
    });
});

/*
    채팅방 신고 모달
*/
$(document).ready(function () {
    $('#reportRoomModal').hide();

    $('#chattingRoomSet-controller .chattingRoomReport').on('click', function () {
        $('#reportRoomModal').fadeIn();
    });

    $('#reportRoomModal .goback input:nth-child(1)').on('click', function () {
        $('#reportRoomModal').fadeOut();
    });
});
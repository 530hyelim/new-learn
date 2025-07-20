/*
    채팅방 설정
*/
/*
    채팅방 설정 컨트롤러
*/
$(document).ready(function () {
    $('#chattingRoomSet-controller').css('display', 'none');

    $('.room-inf button').on('click', function(event){
        event.stopPropagation();

        $('#chattingRoomSet-controller').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px',
            display: 'flex'
        });
    });

    $(document).on('click',function() {
        $('#chattingRoomSet-controller').css('display','none');
    });

    $('#chattingRoomSet-controller').on('click',function(event) {
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

    $(
        '#chattingRoomSet-controller .chattingRoomReport,' + 
        '#otherChattingOption-controller .chattingReport,' +
        '#otherImgFileChattingOption-controller .chattingReport'
    ).on('click', function () {
        $('#reportRoomModal').fadeIn();
    });

    $('#reportRoomModal .goback input:nth-child(1)').on('click', function () {
        $('#reportRoomModal').fadeOut();
    });
});

/*
    채팅 컨트롤러
*/
/*
    내채팅
*/
// $(document).ready(function () {
//     $('#myChattingOption-controller').css('display', 'none');

//     $('.chatting-area .text-chatting').on('contextmenu', function(event){
//         event.preventDefault();
//         event.stopPropagation();
//         $('#myChattingOption-controller').css({
//             top: event.pageY + 'px',
//             left: event.pageX + 'px',
//             display: 'flex'
//         });
//     });

//     $(document).on('click',function() {
//         $('#myChattingOption-controller').css('display','none');
//     });

//     $('#myChattingOption-controller').on('click',function(event) {
//         event.stopPropagation();
//     });
// });

/*
    남채팅
*/
$(document).ready(function(){
    $('#otherChattingOption-controller').css('display', 'none');

    $('.chatting-area .text-chatting').on('contextmenu', function(event){
        event.preventDefault();
        event.stopPropagation();
        $('#otherChattingOption-controller').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px',
            display: 'flex'
        });
    });

    $(document).on('click',function() {
        $('#otherChattingOption-controller').css('display','none');
    });

    $('#otherChattingOption-controller').on('click',function(event){
        event.stopPropagation();
    });
});

/*
    이미지, 파일 채팅컨트롤러
*/
/*
    내채팅
*/
// $(document).ready(function(){
//     $('#myImgFileChattingOption-controller').css('display','none');

//     $('.chatting-area .imgfile-chatting').on('contextmenu',function(event) {
//         event.preventDefault();
//         event.stopPropagation();
//         $('#myImgFileChattingOption-controller').css({
//             top: event.pageY + 'px',
//             left: event.pageX + 'px',
//             display: 'flex'});
//     });

//     $(document).on('click',function() {
//         $('#myImgFileChattingOption-controller').css('display','none');
//     });

//     $('#myImgFileChattingOption-controller').on('click',function(event){
//         event.stopPropagation();
//     });
// });
/*
    남채팅
*/
$(document).ready(function(){
    $('#otherImgFileChattingOption-controller').css('display','none');

    $('.chatting-area .imgfile-chatting').on('contextmenu',function(event) {
        event.preventDefault();
        event.stopPropagation();
        $('#otherImgFileChattingOption-controller').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px',
            display: 'flex'});
    });

    $(document).on('click',function() {
        $('#otherImgFileChattingOption-controller').css('display','none');
    });

    $('#otherImgFileChattingOption-controller').on('click',function(event){
        event.stopPropagation();
    });
});

/*
    공지 컨트롤러
*/
$(document).ready(function(){
    $('#chattingNotice-controller').css('display','none');

    $('.chatting-area .chatting-notice').on('contextmenu',function(event){
        event.preventDefault();
        event.stopPropagation();
        $('#chattingNotice-controller').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px',
            display: 'flex'});
    });

    $(document).on('click',function(){
        $('#chattingNotice-controller').css('display','none');
    });

    $('#chattingNotice-controller').on('click',function(event){
        event.stopPropagation();
    });
});
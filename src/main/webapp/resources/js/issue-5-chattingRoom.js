/*
    채팅방 설정
*/
/*
    채팅방 설정 컨트롤러
*/
$(document).ready(function () {
    $('#controller_set').css('display', 'none');

    $('#container .main .room_inf .room_set').on('click', function(event){
        event.stopPropagation();

        $('#controller_set').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px',
            display: 'flex'
        });
    });

    $(document).on('click',function() {
        $('#controller_set').css('display','none');
    });

    $('#controller_set').on('click',function(event) {
        event.stopPropagation();
    });
});
/*
    1. 방장
*/
$(document).ready(function () {
    // 모달 숨김
    $('#chattingroomset_modal_creater').hide();

    // 버튼 클릭 시 모달 보이기
    $('#controller_set .chattingroom_set').on('click', function () {
        $('#chattingroomset_modal_creater').fadeIn();
    });

    // 취소 버튼
    $('#chattingroomset_modal_creater .go_back input:nth-child(1)').on('click', function () {
        $('#chattingroomset_modal_creater').fadeOut();
    });
});

/*
    2. 방장x
*/
// $(document).ready(function () {
//     $('#chattingroomset_modal_member').hide();

//     $('#controller_set .chattingroom_set').on('click', function () {
//         $('#chattingroomset_modal_member').fadeIn();
//     });

//     $('#chattingroomset_modal_member .go_back input:nth-child(1)').on('click', function () {
//         $('#chattingroomset_modal_member').fadeOut();
//     });
// });

/*
    채팅방 신고 모달
*/
$(document).ready(function () {
    $('#reportroom_modal').hide();

    $(
        '#controller_set .chattingroom_report,' + 
        '#otherchattingoption_controller .chatting_report,' +
        '#otherimgfilechattingoption_controller .chatting_report'
    ).on('click', function () {
        $('#reportroom_modal').fadeIn();
    });

    $('#reportroom_modal .go_back input:nth-child(1)').on('click', function () {
        $('#reportroom_modal').fadeOut();
    });
});

/*
    채팅 컨트롤러
*/
/*
    내채팅
*/
// $(document).ready(function () {
//     $('#mychattingoption_controller').css('display', 'none');

//     $('.chatting_main .chatting_text').on('contextmenu', function(event){
//         event.preventDefault();
//         event.stopPropagation();
//         $('#mychattingoption_controller').css({
//             top: event.pageY + 'px',
//             left: event.pageX + 'px',
//             display: 'flex'
//         });
//     });

//     $(document).on('click',function() {
//         $('#mychattingoption_controller').css('display','none');
//     });

//     $('#mychattingoption_controller').on('click',function(event) {
//         event.stopPropagation();
//     });
// });

/*
    남채팅
*/
$(document).ready(function(){
    $('#otherchattingoption_controller').css('display', 'none');

    $('.chatting_main .chatting_text').on('contextmenu', function(event){
        event.preventDefault();
        event.stopPropagation();
        $('#otherchattingoption_controller').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px',
            display: 'flex'
        });
    });

    $(document).on('click',function() {
        $('#otherchattingoption_controller').css('display','none');
    });

    $('#otherchattingoption_controller').on('click',function(event){
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
//     $('#myimgfilechattingoption_controller').css('display','none');

//     $('.chatting_main .chatting_img_file').on('contextmenu',function(event) {
//         event.preventDefault();
//         event.stopPropagation();
//         $('#myimgfilechattingoption_controller').css({
//             top: event.pageY + 'px',
//             left: event.pageX + 'px',
//             display: 'flex'});
//     });

//     $(document).on('click',function() {
//         $('#myimgfilechattingoption_controller').css('display','none');
//     });

//     $('#myimgfilechattingoption_controller').on('click',function(event){
//         event.stopPropagation();
//     });
// });
/*
    남채팅
*/
$(document).ready(function(){
    $('#otherimgfilechattingoption_ontroller').css('display','none');

    $('.chatting_main .chatting_img_file').on('contextmenu',function(event) {
        event.preventDefault();
        event.stopPropagation();
        $('#otherimgfilechattingoption_controller').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px',
            display: 'flex'});
    });

    $(document).on('click',function() {
        $('#otherimgfilechattingoption_controller').css('display','none');
    });

    $('#otherimgfilechattingoption_controller').on('click',function(event){
        event.stopPropagation();
    });
});

/*
    공지 컨트롤러
*/
$(document).ready(function(){
    $('#chattingnotice_controller').css('display','none');

    $('.chatting_main .chatting_notice_top').on('click',function(event){
        event.preventDefault();
        event.stopPropagation();
        $('#chattingnotice_controller').css({
            top: event.pageY + 'px',
            left: event.pageX + 'px',
            display: 'flex'});
    });

    $(document).on('click',function(){
        $('#chattingnotice_controller').css('display','none');
    });

    $('#chattingnotice_controller').on('click',function(event){
        event.stopPropagation();
    });
});
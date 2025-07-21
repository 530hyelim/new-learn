<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>chattingRoomList</title>
    <link rel="stylesheet" href="/page/css/chattingRoomList.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/page/js/chattingRoomList.js"></script>
</head>
<body>
    <div class="container">
        <div class="header">
            <div class="header_logo">new Learn();</div>
            <div class="header_title">KH 자바스터디 G반</div>
            <div class="header_back"><button class="header_back_button">X</button></div>
        </div>
        <div class="main">
            <div class="friend_list">
                <div class="friend_top">
                    <div>친구목록</div>
                    <div>(30명)</div>
                    <button>채팅방 만들기</button>
                </div>
                <div class="friend_mid">
                    <ul>
                        <li class="friend_inf">
                            <div class="friend_picture">사진</div>
                            <div class="friend_name_ps">
                                <div class="friend_name">이름</div>
                                <div class="friend_ps">상메</div>
                            </div>
                        </li>
                        <li class="friend_inf">
                            <div class="friend_picture">사진</div>
                            <div class="friend_name_ps">
                                <div class="friend_name">이름</div>
                                <div class="friend_ps">상메</div>
                            </div>
                        </li>
                        <li class="friend_inf">
                            <div class="friend_picture">사진</div>
                            <div class="friend_name_ps">
                                <div class="friend_name">이름</div>
                                <div class="friend_ps">상메</div>
                            </div>
                        </li>
                        <li class="friend_inf">
                            <div class="friend_picture">사진</div>
                            <div class="friend_name_ps">
                                <div class="friend_name">이름</div>
                                <div class="friend_ps">상메</div>
                            </div>
                        </li>
                        <li class="friend_inf">
                            <div class="friend_picture">사진</div>
                            <div class="friend_name_ps">
                                <div class="friend_name">이름</div>
                                <div class="friend_ps">상메</div>
                            </div>
                        </li>
                        <li class="friend_inf">
                            <div class="friend_picture">사진</div>
                            <div class="friend_name_ps">
                                <div class="friend_name">이름</div>
                                <div class="friend_ps">상메</div>
                            </div>
                        </li>
                        <li class="friend_inf">
                            <div class="friend_picture">사진</div>
                            <div class="friend_name_ps">
                                <div class="friend_name">이름</div>
                                <div class="friend_ps">상메</div>
                            </div>
                        </li>
                        <li class="friend_inf">
                            <div class="friend_picture">사진</div>
                            <div class="friend_name_ps">
                                <div class="friend_name">이름</div>
                                <div class="friend_ps">상메</div>
                            </div>
                        </li>
                        <li class="friend_inf">
                            <div class="friend_picture">사진</div>
                            <div class="friend_name_ps">
                                <div class="friend_name">이름</div>
                                <div class="friend_ps">상메</div>
                            </div>
                        </li>
                        <li class="friend_inf">
                            <div class="friend_picture">사진</div>
                            <div class="friend_name_ps">
                                <div class="friend_name">이름</div>
                                <div class="friend_ps">상메</div>
                            </div>
                        </li>
                        <li class="friend_inf">
                            <div class="friend_picture">사진</div>
                            <div class="friend_name_ps">
                                <div class="friend_name">이름</div>
                                <div class="friend_ps">상메</div>
                            </div>
                        </li>
                        
                    </ul>
                </div>
            </div>
            <div class="chattingroom_list">
                <div class="chattingroomlist_top">
                    <div>채팅방 목록</div>
                </div>
                <div class="chattingroomlist_sub">
                    <ul>
                        <li>방 제목</li>
                        <li>개설자</li>
                        <li>참여자 수</li>
                        <li>공개여부</li>
                        <li>알림</li>
                        <li>설정</li>
                    </ul>
                </div>
                <div class="chattingroomlist_mid">
                    <ul>
                        <li>
                            <div class="creater_picture">사진</div>
                            <div class="room_title">방 제목</div>
                            <div class="room_alarm">새소식</div>
                            <div class="creater_name">개설자</div>
                            <div class="member_count">참여자 수</div>
                            <div class="open_close">공개여부</div>
                            <input type="button" class="room_alarm_button" value="알림">
                            <input type="button" class="room_set_button" value="설정">
                        </li>
                        <li>
                            <div class="creater_picture">사진</div>
                            <div class="room_title">방 제목</div>
                            <div class="room_alarm">새소식</div>
                            <div class="creater_name">개설자</div>
                            <div class="member_count">참여자 수</div>
                            <div class="open_close">공개여부</div>
                            <input type="button" class="room_alarm_button" value="알림">
                            <input type="button" class="room_set_button" value="설정">
                        </li>
                        <li>
                            <div class="creater_picture">사진</div>
                            <div class="room_title">방 제목</div>
                            <div class="room_alarm">새소식</div>
                            <div class="creater_name">개설자</div>
                            <div class="member_count">참여자 수</div>
                            <div class="open_close">공개여부</div>
                            <input type="button" class="room_alarm_button" value="알림">
                            <input type="button" class="room_set_button" value="설정">
                        </li>
                        <li>
                            <div class="creater_picture">사진</div>
                            <div class="room_title">방 제목</div>
                            <div class="room_alarm">새소식</div>
                            <div class="creater_name">개설자</div>
                            <div class="member_count">참여자 수</div>
                            <div class="open_close">공개여부</div>
                            <input type="button" class="room_alarm_button" value="알림">
                            <input type="button" class="room_set_button" value="설정">
                        </li>
                        <li>
                            <div class="creater_picture">사진</div>
                            <div class="room_title">방 제목</div>
                            <div class="room_alarm">새소식</div>
                            <div class="creater_name">개설자</div>
                            <div class="member_count">참여자 수</div>
                            <div class="open_close">공개여부</div>
                            <input type="button" class="room_alarm_button" value="알림">
                            <input type="button" class="room_set_button" value="설정">
                        </li>
                        <li>
                            <div class="creater_picture">사진</div>
                            <div class="room_title">방 제목</div>
                            <div class="room_alarm">새소식</div>
                            <div class="creater_name">개설자</div>
                            <div class="member_count">참여자 수</div>
                            <div class="open_close">공개여부</div>
                            <input type="button" class="room_alarm_button" value="알림">
                            <input type="button" class="room_set_button" value="설정">
                        </li>
                        <li>
                            <div class="creater_picture">사진</div>
                            <div class="room_title">방 제목</div>
                            <div class="room_alarm">새소식</div>
                            <div class="creater_name">개설자</div>
                            <div class="member_count">참여자 수</div>
                            <div class="open_close">공개여부</div>
                            <input type="button" class="room_alarm_button" value="알림">
                            <input type="button" class="room_set_button" value="설정">
                        </li>
                        <li>
                            <div class="creater_picture">사진</div>
                            <div class="room_title">방 제목</div>
                            <div class="room_alarm">새소식</div>
                            <div class="creater_name">개설자</div>
                            <div class="member_count">참여자 수</div>
                            <div class="open_close">공개여부</div>
                            <input type="button" class="room_alarm_button" value="알림">
                            <input type="button" class="room_set_button" value="설정">
                        </li>
                        <li>
                            <div class="creater_picture">사진</div>
                            <div class="room_title">방 제목</div>
                            <div class="room_alarm">새소식</div>
                            <div class="creater_name">개설자</div>
                            <div class="member_count">참여자 수</div>
                            <div class="open_close">공개여부</div>
                            <input type="button" class="room_alarm_button" value="알림">
                            <input type="button" class="room_set_button" value="설정">
                        </li>
                    </ul>
                </div>
                <div class="chattingroomlist_bottom">
                    <form action="" method="get">
                        <select name="searchType">
                            <option value="title">제목</option>
                            <option value="member">참여자</option>
                        </select>
                        <input type="search" name="keyword">
                        <input type="submit" value="검색">
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!--
        채팅방 만들기 모달창
    -->
    <div id="createroom_modal">
        <div class="modal_name"><span>채팅방 만들기</span></div>
        <div class="room_title">방 제목 입력</div>
        <input type="text" name="" id="" class="input_room_title">
        <div class="create_open_close">
            <div class="choice_open_close">
                <div class="open-close">공개 여부</div>
                <span class="room_open">공개<input type="checkbox" name="open" id=""></span>
                <span class="room_close">비공개<input type="checkbox" name="close" id=""></span>
            </div>
            <div class="create_passwoard">
                <div>비밀번호</div><input type="text" name="" id="">
            </div>
        </div>
        <div class="member_plus">대화 상대 초대</div>
        <div class="search_name">
            <span>이름 검색</span><input type="text" name="" id="">
        </div>
        <div class="friend_list">
            <div class="friend_choice">
                <ul>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="go_back">
                <input type="button" value="취소">
                <input type="button" value="확인">
            </div>
        </div>
    </div>

    <!--
        친구 프로필 모달창
    -->
    <div id="friend_profile_modal">
        <div class="profile_picture">사진</div>
        <div class="profile_name">이름</div>
        <div class="profile_ps">상메</div>
        <div class="profile_option">
            <div class="option1"><input type="button" value="함께 하는 클래스 n개"></div>
            <div class="option2"><input type="button" value="1:1 채팅"></div>
        </div>
        <div class="back"><input type="button" value="닫기"></div>
    </div>
    <div id="together_class">
        <div>KH자바스터디 G반</div>
        <div class="line"></div>
        <div>파이썬으로 GPT 혼...</div>
    </div>

    <!--
        채팅방 설정 컨트롤러
    -->
    <div id="chattingroomset_controller">
        <div class="chattingroomset">설정</div>
        <div class="line"></div>
        <div class="chattingroomexit">나가기</div>
        <div class="line"></div>
        <div class="chattingroomreport">신고</div>
    </div>

    <!--
        채팅방 설정 모달
    -->
    <!--
        1. 방장
    -->
    <div id="setroommodal_creater">
        <div class="create_room_modal"><span>채팅방 설정</span></div>
        <div class="create_room_title">방 제목 변경</div>
        <input type="text" name="" id="" class="room_title_text">
        <div class="create_open_close">
            <div class="open_close">
                <div class="choice_open_class">공개 여부</div>
                <span class="open_room">공개<input type="checkbox" name="open" id=""></span>
                <span class="close_room">비공개<input type="checkbox" name="close" id=""></span>
            </div>
            <div class="create_room_passwoard">
                <div>비밀번호</div><input type="text" name="" id="">
            </div>
        </div>
        <div class="member_plus">대화 상대 초대</div>
        <div class="search_name">
            <span>이름 검색</span><input type="text" name="" id="">
        </div>
        <div class="friend_list">
            <div class="friend_choice">
                <ul>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="go_back">
            <input type="button" value="취소">
            <input type="button" value="확인">
        </div>
    </div>
    
    <!--
        2. 방장x
    -->
    <div id="setroommodal_member">
        <div class="create_room_modal"><span>채팅방 설정</span></div>
        <div class="create_room_title">방 제목 변경</div>
        <input type="text" name="" id="" class="room_title_text">
        <div class="member_plus">대화 상대 초대</div>
        <div class="search_name">
            <span>이름 검색</span><input type="text" name="" id="">
        </div>
        <div class="friend_list">
            <div class="friend_choice">
                <ul>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                    <li>
                        <div class="friend_picture">사진</div>
                        <div class="friend_name_ps">
                            <div class="friend_name">이름</div>
                            <div class="friend_ps">상메</div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="go_back">
                <input type="button" value="취소">
                <input type="button" value="확인">
            </div>
        </div>
    </div>

    <!--
        채팅방 신고
    -->
    <div id="reportroommodal">
        <div class="modal_name">신고</div>
        <div class="report_reason">신고 사유</div>
        <div class="reasons">
            <div class="reason1"><input type="checkbox" name="" id=""><span>욕설/공격젹인 언어 사용</span></div>
            <div class="reason2"><input type="checkbox" name="" id=""><span>혐오발언</span></div>
            <div class="reason3"><input type="checkbox" name="" id=""><span>음란물 유포</span></div>
            <div class="reason4"><input type="checkbox" name="" id=""><span>불법 정보(도박/사행성)</span></div>
            <div class="reason5"><input type="checkbox" name="" id=""><span>기타</span></div>
            <div class="reason_detail"><textarea name="" id="" placeholder="신고 사유"></textarea></div>
        </div>
        <div class="go_back">
            <input type="button" value="취소">
            <input type="button" value="신고">
        </div>
    </div>
</body>
</html>
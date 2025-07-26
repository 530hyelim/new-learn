<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>chattingRoomList</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/issue-5-chattingRoomList.css" type="text/css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
	    var chatAppContextPath = "${pageContext.request.contextPath}";
	    console.log("Context Path:", chatAppContextPath); // 디버깅용
	    
	    // 현재 로그인된 사용자의 userNo를 JavaScript 변수로 설정
	    var loginUserNo = '${sessionScope.loginMember.userNo}';
	    if (typeof loginUserNo === 'undefined' || loginUserNo === null || loginUserNo === '') {
	        loginUserNo = 3; 
	    }
	    loginUserNo = parseInt(loginUserNo); 

	    console.log("로그인된 사용자 번호 (loginUserNo):", loginUserNo);
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/issue-5-chattingRoomList.js"></script>
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
					<div>(${fn:length(friendList)}명)</div>
					<button>채팅방 만들기</button>
				</div>
				<div class="friend_mid">
					<c:choose>
						<c:when test="${empty friendList}">
							<ul>
								<li>친구가 없습니다.</li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul>
								<c:forEach items="${friendList}" var="friend">
									<li class="friend_inf">
										<div class="friend_picture">${friend.changeName}</div>
										<div class="friend_name_ps">
											<div class="friend_name">${friend.userName}</div>
											<div class="friend_ps">${friend.statusMessage }</div>
										</div>
									</li>
								</c:forEach>
							</ul>
						</c:otherwise>
					</c:choose>
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
				<div class="chattingroomlist_mid" id="mainChattingRoomList">
					<c:choose>
						<c:when test="${empty chattingRoomList}">
							<ul>
								<li>채팅방이 없습니다.</li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul>
								<c:forEach items="${chattingRoomList}" var="chattingRoom">
									<li>
										<div class="creater_picture">${chattingRoom.changeName}</div>
										<div class="room_title">${chattingRoom.chatTitle}</div>
										<div class="room_alarm">${chattingRoom.unreadCount}</div>
										<div class="creater_name">${chattingRoom.userName}</div>
										<div class="member_count">${chattingRoom.participantCount}</div>
										<div class="open_close">${chattingRoom.chatPublic}</div>
										<button class="room_alarm_button">${chattingRoom.chatPublic}</button>
										<button class="room_set_button">알림</button>
									</li>
								</c:forEach>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="chattingroomlist_bottom">
					<form action="" method="get">
					<%--  <form action="${pageContext.request.contextPath}/chat/createRoom" method="POST"> --%>
						<select name="searchType">
							<option value="title">제목</option>
							<option value="member">참여자</option>
						</select>
						<input type="search" name="keyword">
						<input type="submit" value="검색">
						<button id="temp-btn" type="submit">전송</button>
					</form>
				</div>
			</div>
		</div>
	</div>

    <!--
        채팅방 만들기 모달창
    -->
    <div id="createroom_modal">
    	<form action="/chat/createRoom" method="post" id="createRoomForm">
    		<div class="modal_name"><span>채팅방 만들기</span></div>
    		<div class="room_title" >방 제목 입력</div>
    		<input type="text" name="chatTitle" id="inputChatTitle" class="input_room_title">
    		<div class="create_open_close">
			    <div class="choice_open_close">
			    	<div class="open-close">공개 여부</div>
				    <span class="room_open">공개<input type="radio" name="chatPublic" id="radioPublicY" value="Y" checked></span>
				    <span class="room_close">비공개<input type="radio" name="chatPublic" id="radioPublicN" value="N"></span>
			    </div>
			    <div class="create_passwoard" id="chatPwGroup" style="display: none;">
				    <div>비밀번호</div>
				    <input type="password" name="chatPw" id="inputChatPw">
			    </div>
    		</div>
		    <div class="member_plus">대화 상대 초대</div>
			<div class="search_name">
			    <span>이름 검색</span><input type="text" name="searchFriend" id="searchFriendInput">
		    </div>
		    <div class="friend_list">
			    <div class="friend_choice">
				    <c:choose>
					    <c:when test="${empty friendList}">
						    <ul>
						    	<li>친구가 없습니다.</li>
						    </ul>
					    </c:when>
					    <c:otherwise>
						    <ul>
						    	<c:forEach items="${friendList}" var="friend">
								    <li class="friend_inf" data-user-no="${friend.userNo}">
									    <label for="selectFriend_${friend.userNo}" style="display:flex; align-items:center; cursor:pointer; flex-grow:1; justify-content:space-between; width:100%;">
										    <div class="friend_picture">${friend.changeName}</div>
										    <div class="friend_name_ps">
											    <div class="friend_name">${friend.userName}</div>
											    <div class="friend_ps">${friend.statusMessage}</div>
										    </div>
										    <input type="checkbox" name="selectFriendList" value="${friend.userNo}" id="selectFriend_${friend.userNo}">
									    </label>
								    </li>
							    </c:forEach>
						    </ul>
					    </c:otherwise>
				    </c:choose>
			    </div>
		    </div>
    		<div class="go_back">
			    <input type="button" value="취소">
			    <input type="button" value="확인" id="createRoomBtn">
    		</div>
    	</form>
    </div>

    <!--
        친구 프로필 모달창
    -->
    <div id="friend_profile_modal">
        <div class="profile_picture">1</div>
        <div class="profile_name">2</div>
        <div class="profile_ps">3</div>
        <div class="profile_option">
            <div class="option1"><button>함께하는 클래스 2개</button></div>
            <div class="option2"><button>1:1 채팅</button></div>
        </div>
        <div class="back"><input type="button" value="닫기"></div>
    </div>
	<div id="together_class">
		<ul>
			<li>
				<div>4</div>
			</li>
		</ul>
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
        <input type="text" name=""  class="room_title_text_creater">
        <div class="create_open_close">
            <div class="open_close">
                <div class="choice_open_class">공개 여부</div>
                <span class="open_room">공개<input type="checkbox" name="open" ></span>
                <span class="close_room">비공개<input type="checkbox" name="close" ></span>
            </div>
            <div class="create_room_passwoard">
                <div>비밀번호</div><input type="text" name="" >
            </div>
        </div>
        <div class="member_plus">대화 상대 초대</div>
        <div class="search_name">
            <span>이름 검색</span><input type="text" name="" >
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
        <input type="text" name=""  class="room_title_text_member">
        <div class="member_plus">대화 상대 초대</div>
        <div class="search_name">
            <span>이름 검색</span><input type="text" name="" >
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
            <div class="reason1"><input type="checkbox" name="" ><span>욕설/공격젹인 언어 사용</span></div>
            <div class="reason2"><input type="checkbox" name="" ><span>혐오발언</span></div>
            <div class="reason3"><input type="checkbox" name="" ><span>음란물 유포</span></div>
            <div class="reason4"><input type="checkbox" name="" ><span>불법 정보(도박/사행성)</span></div>
            <div class="reason5"><input type="checkbox" name="" ><span>기타</span></div>
            <div class="reason_detail"><textarea name=""  placeholder="신고 사유"></textarea></div>
        </div>
        <div class="go_back">
            <input type="button" value="취소">
            <input type="button" value="신고">
        </div>
    </div>
</body>
<!-- <script>
	$("#temp-btn").on("click", function() {
		$.ajax({
			url: "${pageContext.request.contextPath}/chat/createRoom",
			type: "POST",
			data: {
				//chat_totle : ,
				//chat_public
				userNo: 1
			},
			success: function(result) {
				console.log(result);
			},
			error: function(error) {
				console.log(error);
			}
			
		});
	});
</script> -->
</html>
create table member (
    user_no number primary key,
    user_name varchar2(50) not null,
    ssn varchar2(14) not null,
    phone varchar2(11) not null,
    user_id varchar2(50) not null,
    user_pw varchar2(50) not null,
    email varchar2(50) not null,
    last_login_date date default sysdate,
    member_status char(1) default 'Y',
    join_date date default sysdate,
	user_role varchar2(30) default 'ROLE_USER'
);

CREATE TABLE BEER_STORE (
	menu_no	NUMBER		primary key,
	menu_url	VARCHAR2(300)		NULL,
	last_date	DATE		default sysdate
);

CREATE TABLE CLASS (
	class_no	number		primary key,
	class_name	varchar2(100)		not null,
	teacher_no	number		references member(user_no),
	entry_code	char(5)		NULL,
	create_date	date	DEFAULT sysdate,
	deleted	char(1)		default 'N'
);

CREATE TABLE CLASS_JOIN (
	class_no	number	references class,
	user_no	number		references member,
	class_join_date	date		default sysdate,
	class_role varchar2(30) not null,
    constraint pk_class_join primary key (class_no, user_no)
);

CREATE TABLE CLASS_NOTI (
	class_no	number	references class,
	user_no	number		references member,
	announcement_noti   char(1)   not null   default 'Y',
	assignment_noti   char(1)   not null   default 'Y',
	shared_event_noti   char(1)   not null   default 'Y',
	personal_event_noti   char(1)   not null   default 'Y',
    constraint pk_class_join primary key (class_no, user_no)
);

CREATE TABLE ATTENDANCE (
	user_no	number		references member,
	class_no	number		references class,
	today_date date not null,
	entry_time	date		default sysdate,
	exit_time	date		default sysdate,
    constraint pk_attendance primary key (user_no, class_no, today_date)
);

CREATE TABLE board (
	board_no	number		primary key,
	class_no	number		references class,
	user_no	number		references member,
	category	varchar2(50)		NOT NULL,
	board_title	varchar2(225)		NOT NULL,
	board_content	clob		NOT NULL,
	create_date	date		NOT NULL,
	update_date	date		NOT NULL,
	view_count	number	DEFAULT 0	NOT NULL,
	like_count	number	DEFAULT 0	NOT NULL,
	board_status	char(1)	DEFAULT 'Y'	NOT NULL
);

CREATE TABLE image (
	img_no	number		primary key,
	origin_name	varchar2(1000)		not NULL,
	change_name	varchar2(1000)		not NULL,
	create_date	date		default sysdate
);

CREATE TABLE board_img (
	board_no	number		references board,
	img_no	number		references image,
	img_order	number		default 0
);

CREATE TABLE REPLY (
	reply_no	number		primary key,
	board_no	number		references board,
	user_no	number		references member,
	content	varchar2(500)		NOT NULL,
	like_count	number	DEFAULT 0,
	create_date	date	DEFAULT sysdate,
	mod_date	date	DEFAULT sysdate,
	deleted	char(1)	DEFAULT 'N'
);

CREATE TABLE persistent_login (
	token	varchar2(64)		NOT NULL,
	user_no	number		references member,
	series	varchar2(64)		primary key,
	last_used	date default sysdate
);

CREATE TABLE reply_img (
	reply_no	number		primary key,
	img_no	number		references image
);

CREATE TABLE CHAT_ROOM (
	chat_room_no	number		primary key,
	class_no	number		references class,
	chat_title	varchar2(100)		not null,
	create_date	date		default sysdate,
	chat_pw	varchar2(50)		not NULL,
	chat_public	char(1)		not null,
	deleted char(1)		default 'N'
);

CREATE TABLE CHAT_JOIN (
	user_no	number		references member,
	chat_room_no	number		references chat_room,
	join_date	date		default sysdate,
	out_date	date		default sysdate
);

CREATE TABLE CHAT_MESSAGE (
	message_no	number		primary key,
	chat_room_no	number		references chat_room,
	user_no	number		references member,
	send_date	date default sysdate,
	content	varchar2(2000)		not null,
	deleted	char(1)		default 'N'
);

CREATE TABLE REPORT (
	report_no	number		primary key,
	reply_no	number		references reply,
	board_no	number		references board,
	message_no	number		references chat_message,
	user_no	number		references member,
	report_content	varchar2(200)		not null,
	report_time	date		default sysdate,
	report_status	char(1)		default 'N'
);

CREATE TABLE CALENDAR (
	event_no	number		primary key,
	class_no	number		references class,
	user_no	number		references member,
	event_name	varchar2(100)		not null,
	start_date	date		NOT NULL,
	end_date	date		NOT NULL,
	content	varchar2(225)		,
	place	varchar2(225)		,
	create_date	date	DEFAULT SYSDATE,
	event_type	varchar2(10)		NOT NULL,
	visibility	varchar2(225)		NOT NULL,
	num_ppl	number,
	join_deadline	date	not null
);

CREATE TABLE mypage (
	mypage_no	number		primary key,
	user_no	number		references member,
	mypage_name	varchar2(200)		not null,
	status_message	varchar2(1000)		NULL
);

CREATE TABLE friend (
	user_no	number		references member,
	friend_user_no	number		references member,
	request_date	date		default sysdate,
	response_date	date,
    constraint pk_friend primary key (user_no, friend_user_no)
);

CREATE TABLE mypage_img (
	mypage_no	number		references mypage,
	img_no	number		references image,
	type	varchar2(50)		not null,
    constraint pk_mypage_img primary key (img_no)
);

CREATE TABLE subscription (
	sub_no	number		primary key,
	class_no	number		references class,
	user_no	number		references member,
	dont_disturb  char(1)     not null    default 'N',
	guestbook_noti  char(1)     not null    default 'Y',
	chat_noti  char(1)     not null    default 'Y',
	friend_request_noti  char(1)     not null    default 'Y',
	class_invitation_noti  char(1)     not null    default 'Y',
	mapping_url	varchar2(225)		NULL
);


CREATE TABLE EVENT_JOIN_MEMBER (
	event_no	number		references calendar,
	user_no	number		references member,
	join_date	date	DEFAULT sysdate,
    constraint pk_event_join_member primary key (event_no, user_no)
);

CREATE TABLE repository (
	repo_no	number		primary key,
	mypage_no	number		references mypage,
	dir_name	varchar2(100)		not null
);

CREATE TABLE guest_book (
	gb_no	number		primary key,
	mypage_no	number		references mypage,
	user_no	number		references member,
	content	varchar2(1000)		not null,
	create_date	date		default sysdate,
	visibility	char(1) default 'Y',
	deleted	char(1)		default 'N'
);

CREATE TABLE ASSIGNMENT (
	assignment_no	number		primary key,
	class_no	number		references class,
	assignment_title	varchar2(200)		not null,
	assignment_details	clob		not null,
	create_date	date		default sysdate,
	start_date	date		not null,
	end_date	date		not null,
	deleted	char(1)		default 'Y'
);

CREATE TABLE ASSIGNMENT_SUBMISSION (
	submission_no	number		primary key,
	assignment_no	number		references assignment,
	user_no	number		references member,
	submission_date	date		default sysdate
);

CREATE TABLE upload_file (
	file_no	number		primary key,
	submission_no	number		references assignment_submission,
	repo_no	number		references repository,
	message_no	number		references chat_message,
	origin_name	varchar2(225)		not null,
	change_name	varchar2(225)		not NULL,
	visibility	varchar2(10)	DEFAULT 'public',
	create_date	date		default sysdate,
    deleted char(1) default 'N'
);

CREATE TABLE AI (
	model_no	number		primary key,
	model_name	varchar2(100)		not null,
	active_status	char(1)		NULL
);

CREATE TABLE AI_USAGE (
	user_no	number		references member,
	model_no	number		references ai,
	num_used_token_no	number		default 0
);

-------------------- 실행 -------------------------

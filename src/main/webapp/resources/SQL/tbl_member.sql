--tbl_memebr 테이블 생성
create table tbl_member(
    userid varchar2(50) primary key --회원 아이디
    , userpw varchar2(50) not null --회원 비번
    ,username varchar2(30) not null --회원 이름
    ,email varchar2(100) --전자우편
    ,regdate date default sysdate --가입날짜
);

select * from tbl_member; 

--tbl_board 게시판 테이블 생성
create table tbl_board(
 bno number(35) primary key --게시판 번호
 ,writer varchar2(50) not null --작성자
 ,title varchar2(200) not null --글제목
 ,content varchar(4000) not null --글내용
 ,viewcnt number(38) default 0 --조회수, default0 제약조건을 설정해서 해당 컬럼에 굳이 레코드를 저장하지 않아도 기본값 0이 저장됨
 ,regdate date default sysdate --글등록 날짜
);

select * from tbl_board order by bno desc;

--댓글수를 카운터 해서 저장할 replycnt 컬럼을 추가
alter table tbl_board
add(replycnt number(38) default 0); --댓글이 추가되면 +1, 댓글이 삭제되면 -1

-- 게시판 번호에 해당하는 댓글수를 카운터해서 replycnt 새롭게 추가된 컬럼 레코드값으로 수정
update tbl_board set replycnt = (select count(rno) from tbl_reply where bno=tbl_board.bno)
where bno>0;

commit;

--bno_seq 시퀀스 다음 번호값 확인
select bno_seq.nextval as "시퀀스 번호값"from dual;


--tbl_reply 댓글 테이블 생성
create table tbl_reply(
rno number(38) primary key --댓글 번호
, bno number(38) default 0 -- 외래키 제약조건을 추가설정해서 tbl_board 게시판 테이블에 기본키로 설정된 bno컬럼 레코드 값인
--게시판 번호값만 이 컬럼 레코드 번호값으로 저장됨. 즉 주종관계 설정.
, replyer varchar2(50) not null -- 댓글 작성자
,replaytext varchar2(4000) not null -- 댓글 내용
, regdate date --댓글 등록날짜
, updatedate date --댓글 수정날짜
);
alter table tbl_reply rename column replaytext to replytext;
select * from tbl_reply order by rno desc;    --댓글 번호를 기준으로 내림차순 정렬

--tbl_reply 테이블 bno컬럼 외래키 추가 설정
alter table tbl_reply add constraint tbl_reply_bno_fk
foreign key(bno) references tbl_board(bno);

--rno_seq 시퀀스 생성, 시퀀스는 번호 발생기이다.
create sequence rno_seq 
start with 1 --1부터 시작
increment by 1 --1씩 증가
nocache; -- 임시메모리를 사용하지 않아야 함

--rno_seq 시퀀스 다음 번호값 확인
select rno_seq.nextval as "시퀀스 번호값 확인" from dual;  

--스프링 AOP와 트랜잭션 실습을 위한 샘플 테이블 생성
--tbl_user 테이블 생성
create table tbl_user(
uid2 varchar2(50) primary key --회원 아이디
, upw varchar2(100) not null --비번
,uname varchar2(50) not null --회원 이름
,upoint number(38) default 0 -- 메시지가 보내지면 포인트 점수 10점 업데이트 => 메시지가  insert되면 메시지 하나당 포인트 점수 10점 update는  트랜잭션 적용대상
);
insert into tbl_user(uid2, upw ,uname) values('user00','56789','홍길동');
insert into tbl_user(uid2, upw, uname) values('user01','234589','이순신');

commit;  

select * from tbl_user order by uid2 asc;



--tbl_message 테이블 생성
create table tbl_message(
mid number(38) primary key
,targetid varchar2(50) not null -- 외래키 인 foreign key 추가 설정 => tbl_user테이블의 uid2컬럼 회원아이디값만 저장됨
,sender varchar2(50) not null -- 메시지를 보낸 사람
,message varchar2(4000) --보낸 메시지
, senddate date --보낸 날짜

);

select * from tbl_message order by mid asc;

delete from tbl_message where mid = 3;

--targetid 컬럼에 추가적인 외래키 설정
alter table tbl_message add constraint tbl_message_targetid_fk
foreign key(targetid) references tbl_user(uid2);

--mid_no_seq 시퀀스 생성
create sequence mid_no_seq
start with 1 --1부터 시작
increment by 1 --1씩 증가
nocache; --임식 메모리 사용 안함

--mid_no_seq 시퀀스 다음 번호값 확인
select mid_no_seq.nextval as "시퀀스 번호값 확인" from dual;

--tbl_gongji 테이블 작성
create table tbl_gongji(
    gongji_no number(38) primary key
    ,gongji_name varchar2(50) not null
    ,gongji_title varchar2(200) not null
    ,gongji_cont varchar2(4000) not null
    ,gongji_hit number(38) default 0
    ,gongji_date date default sysdate
);

create sequence gongji_no_seq
start with 1
increment by 1
nocache;

select gongji_no_seq.nextval as "시퀀스 번호값 확인" from dual;

drop sequence gongji_no_seq;
drop table tbl_gongji;


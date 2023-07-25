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

select * from tbl_reply order by rno desc;  --댓글 번호를 기준으로 내림차순 정렬

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


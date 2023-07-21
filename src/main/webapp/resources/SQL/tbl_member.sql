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



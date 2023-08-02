-- tbl_gongji 테이블 작성
create table tbl_gongji(
  gongji_no  number(38) primary key --공지 번호
  ,gongji_name varchar2(50) not null --공지 작성자
  ,gongji_title varchar2(200) not null --공지 제목
  ,gongji_cont varchar2(4000) not null --공지 내용
  ,gongji_hit number(38) default 0  --조회수
  ,gongji_date date default sysdate --등록날짜
); 

select * from tbl_gongji order by gongji_no desc;

--gongji_no_seq 시퀀스 생성
create sequence gongji_no_seq
start with 1 --1부터 시작
increment by 1 --1씩 증가
nocache; --임시 메모리를 사용안함.

--gongji_no_seq 시퀀스 다음 번호값 확인
select gongji_no_seq.nextval as "시퀀스번호값확인" from dual;

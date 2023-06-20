drop table board CASCADE CONSTRAINTS;

create table board(
    num number primary key,
    name varchar2(100),
    pwd varchar2(100),
    email varchar2(100),
    subject varchar2(255),
    content clob,
    ipAddr varchar2(100),
    hitCount number,
    created date
);

select * from board;

commit;


select * from board;

--num 컬럼에서 가장 큰 num 을 구하는 쿼리문
select nvl(max(num), 0) from board;


rollback;
TRUNCATE table board;  --commit 했을 경우 빈 테이블 만들기

insert into board values(1, 'bbo', '1111', 'bbo1234@gmail.com', '제목', '내용', '111', 10, sysdate);
insert into board values(2, 'bbo', '1111', 'bbo1234@gmail.com', '제목', '내용', '111', 10, sysdate);

-- 전체 게시물의 갯수 출력
select nvl(count(*), 0) from board where subject like '%춘식%';
select nvl(count(*), 0) from board where content like '%집%';


--검색 페이지 보여주기 sql 구문 가공
select num, name, subject, hitCount, to_char(created, 'YYYY-MM-DD') created 
from board
where subject like '%춘식%'
order by num desc;


select rownum rnum, data.* from (
select num, name, subject, hitCount, to_char(created, 'YYYY-MM-DD') created 
from board
where subject like '%춘식%'
order by num desc) data;

select * from(
     select rownum rnum, data.* from (
        select num, name, subject, hitCount, to_char(created, 'YYYY-MM-DD') created 
        from board
        where subject like '%춘식%'
        order by num desc
        ) data
)
where rnum >= 1 and rnum <= 2;
-- 검색할 때 페이징 처리 때문에 하는 작업

--조회수 늘리기
update board set hitCount = hitCount + 1 where num = 1;

select * from board;

--조회

select * from board where num = 1;

rollback;

update board set name='퇴근', pwd='1234', email='bbo1234@gmail.com', subject='하하하하하', content='냉무' where num = 1;

delete board where num = 7;















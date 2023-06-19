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

--num �÷����� ���� ū num �� ���ϴ� ������
select nvl(max(num), 0) from board;


rollback;
TRUNCATE table board;  --commit ���� ��� �� ���̺� �����

insert into board values(1, 'bbo', '1111', 'bbo1234@gmail.com', '����', '����', '111', 10, sysdate);
insert into board values(2, 'bbo', '1111', 'bbo1234@gmail.com', '����', '����', '111', 10, sysdate);

-- ��ü �Խù��� ���� ���
select nvl(count(*), 0) from board where subject like '%���%';
select nvl(count(*), 0) from board where content like '%��%';


--�˻� ������ �����ֱ� sql ���� ����
select num, name, subject, hitCount, to_char(created, 'YYYY-MM-DD') created 
from board
where subject like '%���%'
order by num desc;


select rownum rnum, data.* from (
select num, name, subject, hitCount, to_char(created, 'YYYY-MM-DD') created 
from board
where subject like '%���%'
order by num desc) data;

select * from(
     select rownum rnum, data.* from (
        select num, name, subject, hitCount, to_char(created, 'YYYY-MM-DD') created 
        from board
        where subject like '%���%'
        order by num desc
        ) data
)
where rnum >= 1 and rnum <= 2;
-- �˻��� �� ����¡ ó�� ������ �ϴ� �۾�





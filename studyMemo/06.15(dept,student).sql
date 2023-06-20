drop table dept cascade constraints;

create table dept(
    deptno varchar2(100) primary key,
    dept varchar2(100),
    loc varchar2(100)
);

insert into dept values('101', '개발부', '인천');
insert into dept values('102', '홍보부', '서울');
insert into dept values('103', '연구부', '부산');

select * from dept;

commit;

create table student(
    name varchar2(100) primary key,
    age number,
    java varchar2(100),
    oracle varchar2(100)
);

insert into student values('이유나', 20, 'A+', 'C');
insert into student values('김현우', 23, 'B+', 'A');
insert into student values('이지원', 21, 'F', 'A+');

select * from student;

commit;










drop table dept cascade constraints;

create table dept(
    deptno varchar2(100) primary key,
    dept varchar2(100),
    loc varchar2(100)
);

insert into dept values('101', '���ߺ�', '��õ');
insert into dept values('102', 'ȫ����', '����');
insert into dept values('103', '������', '�λ�');

select * from dept;

commit;

create table student(
    name varchar2(100) primary key,
    age number,
    java varchar2(100),
    oracle varchar2(100)
);

insert into student values('������', 20, 'A+', 'C');
insert into student values('������', 23, 'B+', 'A');
insert into student values('������', 21, 'F', 'A+');

select * from student;

commit;










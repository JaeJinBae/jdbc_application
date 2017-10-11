select * from department;
insert into department values(4,"개발",10);
delete from department where deptno=4;

select * from title;
insert into title values(6,"차장");
delete from title where titleno=6;
update title set titlename="부사장" where titleno=6;

select * from employee;
delete from tit where titleno=6;
update employee set manager=4377 where empno=5555;
insert into course(id,created_date, name) values(10001 ,sysdate(),'JPA Hibernate Tutorial');
insert into course(id,created_date, name) values(10002 ,sysdate(),'Microservices Tutorial with Spring Boot');
insert into course(id,created_date, name) values(10003 ,sysdate(),'RabbitMq');
insert into course(id,created_date, name) values(10004 ,sysdate(),'SAP Cloud Platform');
insert into course(id,created_date, name) values(10005 ,sysdate(),'WEB Apps With SAP Ui5');
insert into course(id,created_date, name) values(10006 ,sysdate(),'Docker With JAVA Tutorial');
insert into course(id,created_date, name) values(10007 ,sysdate(),'Kubernetes');

insert into passport(id, number) values(40001 ,'S12342452');
insert into passport(id, number) values(40002 ,'A87239100');
insert into passport(id, number) values(40003 ,'AL1231231');

insert into student(id, name, passport_id) values(20001 ,'Subhasis',40003);
insert into student(id, name, passport_id) values (20002 ,'Atiksh',40002);
insert into student(id, name, passport_id) values(20003 ,'Avilipsha',40001);


insert into review(id, rating, description,course_id) values(50001 ,'5', 'Awesome Course',10001);
insert into review(id, rating, description,course_id) values(50002 ,'4', 'Very Descriptive',10001);
insert into review(id, rating, description,course_id) values(50003 ,'3', 'Can be improved',10003);

insert into student_course(student_id,course_id) values(20001,10004);
insert into student_course(student_id,course_id) values(20001,10002);
insert into student_course(student_id,course_id) values(20003,10002);
insert into student_course(student_id,course_id) values(20003,10001);
insert into student_course(student_id,course_id) values(20002,10003);
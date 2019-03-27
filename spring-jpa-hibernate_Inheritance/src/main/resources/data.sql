insert into course(id, name) values(10001 ,'JPA Hibernate Tutorial');
insert into course(id, name) values(10002 ,'Microservices with Spring Boot Tutorial');
insert into course(id, name) values(10003 ,'RabbitMq');
insert into course(id, name) values(10004 ,'SAP Cloud Platform');

insert into passport(id, number) values(40001 ,'S12342452');
insert into passport(id, number) values(40002 ,'A87239100');
insert into passport(id, number) values(40003 ,'AL1231231');

insert into student(id, name, passport_id) values(20001 ,'Subhasis',40003);
insert into student(id, name, passport_id) values(20002 ,'Atiksh',40002);
insert into student(id, name, passport_id) values(20003 ,'Avilipsha',40001);


insert into review(id, rating, description,course_id) values(50001 ,'5', 'Awesome Course',10001);
insert into review(id, rating, description,course_id) values(50002 ,'4', 'Very Descriptive',10001);
insert into review(id, rating, description,course_id) values(50003 ,'3', 'Can be improved',10003);

insert into student_course(student_id,course_id) values(20001,10004);
insert into student_course(student_id,course_id) values(20001,10002);
insert into student_course(student_id,course_id) values(20003,10002);
insert into student_course(student_id,course_id) values(20003,10001);
insert into student_course(student_id,course_id) values(20002,10003);
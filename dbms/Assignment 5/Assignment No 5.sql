create table students(Roll_NO number(20) primary key,attendence number(20) not null,status VARCHAR(4));
INSERT INTO students(ROLL_NO,ATTENDENCE) values (1,65);
INSERT INTO students(ROLL_NO,ATTENDENCE) values (2,85);
INSERT INTO students(ROLL_NO,ATTENDENCE) values (3,75);
INSERT INTO students(ROLL_NO,ATTENDENCE) values (4,55);
INSERT INTO students(ROLL_NO,ATTENDENCE) values (5,70);
INSERT INTO students(ROLL_NO,ATTENDENCE) values (6,80);
INSERT INTO students(ROLL_NO,ATTENDENCE) values (7,95);

DECLARE
presenty number(20);
rollno number(20);
BEGIN
rollno:=&rollno;
select ATTENDENCE into presenty from students where ROLL_NO=rollno;
dbms_output.put_line('dance'||presenty);
IF(presenty < 75) THEN
update students set STATUS='D' where ROLL_NO=rollno;
dbms_output.put_line('Term not granted'||presenty);

ELSE
update students set STATUS='ND' where ROLL_NO=rollno;
dbms_output.put_line('Term  granted'||presenty);
END IF;
END;
/*
Enter value for rollno
old   5: rollno:=&roll
new   5: rollno:=1;
dance65
Term not granted 65

PL/SQL procedure successfully completed

SQL> SELECT * FROM students

   ROLL_NO ATTENDENCE
---------- ----------
         1         65
         2         85
         3         75
         4         55
         5         70
         6         80
         7         95
*/

create table acc_master(acc_no number(20) primary key,balance number(20) not null,status VARCHAR(4));

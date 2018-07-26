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

INSERT INTO acc_master (ACC_NO,BALANCE) values (1,10000);
INSERT INTO acc_master (ACC_NO,BALANCE) values (2,20000);
INSERT INTO acc_master (ACC_NO,BALANCE) values (3,30000);
INSERT INTO acc_master (ACC_NO,BALANCE) values (4,40000);
INSERT INTO acc_master (ACC_NO,BALANCE) values (5,50000);

DECLARE
accno number(20);
withdraw number(20);
bal number(20);
cannot_withdraw exception;
BEGIN
withdraw:=&withdraw;
accno:=&accno;
select BALANCE into bal from acc_master where ACC_NO=accno;
dbms_output.put_line('balance = '||bal);
IF((bal-withdraw) < 0) THEN
raise cannot_withdraw;
ELSE
update acc_master set BALANCE=(bal-withdraw) where ACC_NO=accno;
dbms_output.put_line('balance deducted by = '||withdraw);
END IF;
EXCEPTION
when cannot_withdraw then
dbms_output.put_line('cannot withdraw = '||withdraw);
END;

/*
Enter value for withdraw: 1000
old   7: withdraw:=&withdraw;
new   7: withdraw:=1000;
Enter value for accno: 1
old   8: accno:=&accno;
new   8: accno:=1;
dance9800
balance deducted by = 1000

PL/SQL procedure successfully completed.

SQL> /
Enter value for withdraw: 100000
old   7: withdraw:=&withdraw;
new   7: withdraw:=100000;
Enter value for accno: 1
old   8: accno:=&accno;
new   8: accno:=1;
dance8800
cannot withdraw = 100000

PL/SQL procedure successfully completed.
*/












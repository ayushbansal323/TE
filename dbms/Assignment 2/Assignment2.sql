CREATE TABLE branch(branch_name varchar(20) NOT NULL,branch_city varchar(40) NOT NULL, PRIMARY KEY(branch_name) , CHECK (branch_city='pune' OR branch_city='mumbai' OR branch_city='nashik'));
DESC branch;
/*
+-------------+-------------+------+-----+---------+-------+
| Field       | Type        | Null | Key | Default | Extra |
+-------------+-------------+------+-----+---------+-------+
| branch_name | varchar(20) | NO   | PRI | NULL    |       |
| branch_city | varchar(40) | NO   |     | NULL    |       |
+-------------+-------------+------+-----+---------+-------+
2 rows in set (0.00 sec)
*/
CREATE TABLE Account(Acc_no int primary key not null , branch_name varchar(20) not null,balance int not null , FOREIGN KEY (branch_name) REFERENCES branch(branch_name));
DESC Account;
/*
+-------------+-------------+------+-----+---------+-------+
| Field       | Type        | Null | Key | Default | Extra |
+-------------+-------------+------+-----+---------+-------+
| Acc_no      | int(11)     | NO   | PRI | NULL    |       |
| branch_name | varchar(20) | NO   | MUL | NULL    |       |
| balance     | int(11)     | NO   |     | NULL    |       |
+-------------+-------------+------+-----+---------+-------+
3 rows in set (0.00 sec)
*/
create table Depository(acc_no int primary key not null , cust_name varchar(20) not null , FOREIGN KEY (acc_no) REFERENCES Account(Acc_no));
DESC Depository;
/*
+-----------+-------------+------+-----+---------+-------+
| Field     | Type        | Null | Key | Default | Extra |
+-----------+-------------+------+-----+---------+-------+
| acc_no    | int(11)     | NO   | PRI | NULL    |       |
| cust_name | varchar(20) | NO   |     | NULL    |       |
+-----------+-------------+------+-----+---------+-------+
2 rows in set (0.00 sec)
*/
create table customer(cust_name varchar(20) primary key not null,cust_street varchar(40) not null,cust_city varchar(20) not null );
DESC customer;
/* 
+-------------+-------------+------+-----+---------+-------+
| Field       | Type        | Null | Key | Default | Extra |
+-------------+-------------+------+-----+---------+-------+
| cust_name   | varchar(20) | NO   | PRI | NULL    |       |
| cust_street | varchar(40) | NO   |     | NULL    |       |
| cust_city   | varchar(20) | NO   |     | NULL    |       |
+-------------+-------------+------+-----+---------+-------+
3 rows in set (0.00 sec)
*/
create table Borrower(loan_no int primary key AUTO_INCREMENT,cust_name varchar(20) not null ,FOREIGN KEY (cust_name) REFERENCES customer(cust_name));
DESC Borrower;
/*
+-----------+-------------+------+-----+---------+----------------+
| Field     | Type        | Null | Key | Default | Extra          |
+-----------+-------------+------+-----+---------+----------------+
| loan_no   | int(11)     | NO   | PRI | NULL    | auto_increment |
| cust_name | varchar(20) | NO   | MUL | NULL    |                |
+-----------+-------------+------+-----+---------+----------------+
2 rows in set (0.00 sec)
*/
create table Loan(loan_no int primary key not null ,branch_name varchar(20) not null , amount int , FOREIGN KEY (branch_name) REFERENCES branch(branch_name) , FOREIGN KEY (loan_no) REFERENCES Borrower(loan_no));
DESC Loan;
/*
+-------------+-------------+------+-----+---------+-------+
| Field       | Type        | Null | Key | Default | Extra |
+-------------+-------------+------+-----+---------+-------+
| loan_no     | int(11)     | NO   | PRI | NULL    |       |
| branch_name | varchar(20) | NO   | MUL | NULL    |       |
| amount      | int(11)     | YES  |     | NULL    |       |
+-------------+-------------+------+-----+---------+-------+
3 rows in set (0.00 sec)
*/
INSERT INTO branch values('pimpri','pune');
INSERT INTO branch values('akurdi','pune');
INSERT INTO branch values('panvel','mumbai');
INSERT INTO branch values('thane','mumbai');
INSERT INTO branch values('kurla','mumbai');
INSERT INTO branch values('prambak','nashik');
INSERT INTO branch values('ozar','nashik');

INSERT INTO Account values(1001,'pimpri',34500);
INSERT INTO Account values(1002,'akurdi',54500);
INSERT INTO Account values(1003,'panvel',84500);
INSERT INTO Account values(1004,'thane',134500);
INSERT INTO Account values(1005,'ozar',114500);
INSERT INTO Account values(1006,'pimpri',104500);
INSERT INTO Account values(1007,'kurla',100500);
INSERT INTO Account values(1008,'ozar',4500);

INSERT INTO Depository values(1001,'max payne');
INSERT INTO Depository values(1002,'tanuja bedse');
INSERT INTO Depository values(1004,'tanu ghute');
INSERT INTO Depository values(1005,'rohit agarwal');
INSERT INTO Depository values(1006,'nikhil badgujar');
INSERT INTO Depository values(1007,'prerana agale');

INSERT INTO customer values('prerana agale' , '506 HA colony' , 'pune');
INSERT INTO customer values('max payne' , '56 street 56 floor' , 'new york');
INSERT INTO customer values('tanuja bedse' , 'Nandanvan society nakane road deopur ' , 'dhule');
INSERT INTO customer values('tanu ghute' , '304 sunflower sukwani campus' , 'mumbai');
INSERT INTO customer values('rohit agarwal' , '225/453 Sant tukaram nagar' , 'pune');
INSERT INTO customer values('nikhil badgujar' , 'B wing 504' , 'pune');

INSERT INTO Borrower(cust_name) values('max payne');
INSERT INTO Borrower(cust_name) values('tanuja bedse');
INSERT INTO Borrower(cust_name) values('tanu ghute');
INSERT INTO Borrower(cust_name) values('rohit agarwal');

INSERT INTO Loan values(1,'pimpri',500);
INSERT INTO Loan values(2,'akurdi',1000);
INSERT INTO Loan values(3,'thane',300);

/*1*/
SELECT * FROM Loan;
/*
+---------+-------------+--------+
| loan_no | branch_name | amount |
+---------+-------------+--------+
|       1 | pimpri      |    500 |
|       2 | akurdi      |   1000 |
|       3 | thane       |    300 |
+---------+-------------+--------+
3 rows in set (0.00 sec)
*/
/*2*/
SELECT loan_no FROM Loan WHERE amount > 120 AND branch_name = 'akurdi';
/*
+---------+
| loan_no |
+---------+
|       2 |
+---------+
*/
/*3*/
SELECT Loan.loan_no,branch_name,cust_name from Loan inner join Borrower on Borrower.loan_no=Loan.loan_no ;
/*
+---------+-------------+--------------+
| loan_no | branch_name | cust_name    |
+---------+-------------+--------------+
|       2 | akurdi      | tanuja bedse |
|       1 | pimpri      | max payne    |
|       3 | thane       | tanu ghute   |
+---------+-------------+--------------+
*/
/*4*/
SELECT Loan.loan_no,branch_name,cust_name from Loan inner join Borrower on Borrower.loan_no=Loan.loan_no where branch_name='akurdi' order by cust_name;
/*
+---------+-------------+--------------+
| loan_no | branch_name | cust_name    |
+---------+-------------+--------------+
|       2 | akurdi      | tanuja bedse |
+---------+-------------+--------------+
1 row in set (0.00 sec)
*/
/*5*/
SELECT * FROM Depository left join Borrower on Depository.cust_name=Borrower.cust_name ; 
/*
+--------+-----------------+---------+---------------+
| acc_no | cust_name       | loan_no | cust_name     |
+--------+-----------------+---------+---------------+
|   1001 | max payne       |       1 | max payne     |
|   1002 | tanuja bedse    |       2 | tanuja bedse  |
|   1004 | tanu ghute      |       3 | tanu ghute    |
|   1005 | rohit agarwal   |       4 | rohit agarwal |
|   1006 | nikhil badgujar |    NULL | NULL          |
|   1007 | prerana agale   |    NULL | NULL          |
+--------+-----------------+---------+---------------+
6 rows in set (0.00 sec)
*/
/*6*/
SELECT * FROM Depository left join Borrower on Depository.cust_name=Borrower.cust_name where loan_no != 'null';
/*
+--------+---------------+---------+---------------+
| acc_no | cust_name     | loan_no | cust_name     |
+--------+---------------+---------+---------------+
|   1001 | max payne     |       1 | max payne     |
|   1002 | tanuja bedse  |       2 | tanuja bedse  |
|   1004 | tanu ghute    |       3 | tanu ghute    |
|   1005 | rohit agarwal |       4 | rohit agarwal |
+--------+---------------+---------+---------------+
4 rows in set, 3 warnings (0.00 sec)
*/
/*7*/
 SELECT * FROM Depository left join Borrower on Depository.cust_name=Borrower.cust_name where loan_no is null and acc_no!='null' ;
/*
+--------+-----------------+---------+-----------+
| acc_no | cust_name       | loan_no | cust_name |
+--------+-----------------+---------+-----------+
|   1006 | nikhil badgujar |    NULL | NULL      |
|   1007 | prerana agale   |    NULL | NULL      |
+--------+-----------------+---------+-----------+
2 rows in set, 3 warnings (0.00 sec)
*/
/*8*/
SELECT AVG(balance) FROM Account WHERE branch_name='akurdi';
/*
+--------------+
| AVG(balance) |
+--------------+
|   54500.0000 |
+--------------+
1 row in set (0.02 sec)
*/
/*9*/
SELECT branch_name,AVG(balance) FROM Account GROUP BY branch_name ;
/*
+-------------+--------------+
| branch_name | AVG(balance) |
+-------------+--------------+
| akurdi      |   54500.0000 |
| kurla       |  100500.0000 |
| ozar        |   59500.0000 |
| panvel      |   84500.0000 |
| pimpri      |   69500.0000 |
| thane       |  134500.0000 |
+-------------+--------------+
*/
/*10*/
SELECT branch_name,COUNT(*) FROM Account GROUP BY branch_name;
/*
+-------------+----------+
| branch_name | COUNT(*) |
+-------------+----------+
| akurdi      |        1 |
| kurla       |        1 |
| ozar        |        2 |
| panvel      |        1 |
| pimpri      |        2 |
| thane       |        1 |
+-------------+----------+
6 rows in set (0.01 sec)
*/
/*11*/
SELECT branch_name,AVG(balance) FROM Account GROUP BY branch_name HAVING AVG(balance) > 12000;
/*
+-------------+--------------+
| branch_name | AVG(balance) |
+-------------+--------------+
| akurdi      |   54500.0000 |
| kurla       |  100500.0000 |
| ozar        |   59500.0000 |
| panvel      |   84500.0000 |
| pimpri      |   69500.0000 |
| thane       |  134500.0000 |
+-------------+--------------+
6 rows in set (0.00 sec)
/*12*/
SELECT COUNT(*) FROM customer;
/*
+----------+
| COUNT(*) |
+----------+
|        6 |
+----------+
1 row in set (0.00 sec)
*/
/*13*/
SELECT AVG(amount) FROM Loan;
/*
+-------------+
| AVG(amount) |
+-------------+
|    600.0000 |
+-------------+
1 row in set (0.00 sec)
*/
/*14*/
 DELETE FROM Loan WHERE amount between 1300 and 1500;
/*15*/
 DELETE FROM branch where branch_name='nigdi';
/*16*/
create view cust AS SELECT * FROM customer;



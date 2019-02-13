/*1. Create following Tables*/
CREATE TABLE cust_mstr(cust_no INTEGER PRIMARY KEY AUTO_INCREMENT,fname VARCHAR(40) NOT NULL,lname VARCHAR(40) NOT NULL);

CREATE TABLE add_dets(code_no INTEGER PRIMARY KEY,add1 VARCHAR(40) NOT NULL,add2 VARCHAR(40),state VARCHAR(40),city VARCHAR(40),pincode INTEGER,FOREIGN KEY(code_no) REFERENCES cust_mstr(cust_no));

INSERT INTO cust_mstr(fname,lname) values('prerana','agale');
INSERT INTO cust_mstr(fname,lname) values('nikhil','badgujar');
INSERT INTO cust_mstr(fname,lname) values('tanuja','bedse');
INSERT INTO cust_mstr(fname,lname) values('john','wick');
INSERT INTO cust_mstr(fname,lname) values('xyz','pqr');

INSERT INTO add_dets(code_no,add1,state,city,pincode) values(1,'A106 HA colony','maharashtra','pune',411018);
INSERT INTO add_dets(code_no,add1,state,city,pincode) values(2,'B wing 504','maharashtra','pune',411016);
INSERT INTO add_dets(code_no,add1,state,city,pincode) values(3,'Nandanvan society nakane road deopur','maharashtra','dhule',424002);
INSERT INTO add_dets(code_no,add1,state,city,pincode) values(4,'One57, 157 West 57th Street','new york','new york',10022);
INSERT INTO add_dets(code_no,add1,add2,state,city,pincode) values(5,'15/177 Sant tukaram nagar','205 sunflower sukwani campus','maharashtra','pune',411018);
/*Retrieve the address of customer Fname as 'xyz' and Lname as 'pqr'*/
SELECT * FROM cust_mstr INNER JOIN add_dets on cust_no=code_no WHERE fname='xyz' AND lname='pqr';
/*
+---------+-------+-------+---------+---------------------------+------------------------------+-------------+------+---------+
| cust_no | fname | lname | code_no | add1                      | add2                         | state       | city | pincode |
+---------+-------+-------+---------+---------------------------+------------------------------+-------------+------+---------+
|       5 | xyz   | pqr   |       5 | 15/177 Sant tukaram nagar | 205 sunflower sukwani campus | maharashtra | pune |  411018 |
+---------+-------+-------+---------+---------------------------+------------------------------+-------------+------+---------+
1 row in set (0.00 sec)
*/
/*2.Create following Tables*/
CREATE TABLE acc_fd_cust_dets(codeno INTEGER PRIMARY KEY ,acc_fd_no INTEGER,FOREIGN KEY(codeno) REFERENCES cust_mstr(cust_no),FOREIGN KEY(acc_fd_no) REFERENCES fd_dets(fd_sr_no));
CREATE TABLE fd_dets(fd_sr_no INTEGER PRIMARY KEY AUTO_INCREMENT,amt INTEGER NOT NULL);

INSERT INTO fd_dets(amt) values(10000);
INSERT INTO fd_dets(amt) values(20000);
INSERT INTO fd_dets(amt) values(30000);
INSERT INTO fd_dets(amt) values(40000);
INSERT INTO fd_dets(amt) values(50000);
INSERT INTO fd_dets(amt) values(60000);

INSERT INTO acc_fd_cust_dets values(1,1);
INSERT INTO acc_fd_cust_dets values(2,2);
INSERT INTO acc_fd_cust_dets values(3,3);
INSERT INTO acc_fd_cust_dets values(4,4);
INSERT INTO acc_fd_cust_dets values(5,5); 

/*List the customer holding fixed deposit of amount more than 5000*/
SELECT * FROM cust_mstr INNER JOIN acc_fd_cust_dets on cust_no=codeno INNER JOIN fd_dets on acc_fd_no=fd_sr_no WHERE amt>5000;

/*3. Create following Tables*/
CREATE TABLE emp_mstr(e_mpno INTEGER PRIMARY KEY AUTO_INCREMENT,f_name VARCHAR(40) NOT NULL,l_name VARCHAR(40) NOT NULL,m_name VARCHAR(40) NOT NULL,dept VARCHAR(20),desg VARCHAR(20),branch_no INTEGER,FOREIGN KEY(branch_no) REFERENCES branch_mstr(b_no));
CREATE TABLE branch_mstr(name VARCHAR(40),b_no INTEGER PRIMARY KEY AUTO_INCREMENT);

INSERT INTO branch_mstr(name) values('akurdi');
INSERT INTO branch_mstr(name) values('pimpri');
INSERT INTO branch_mstr(name) values('thane');
INSERT INTO branch_mstr(name) values('ozar');
INSERT INTO branch_mstr(name) values('kurla');

INSERT INTO emp_mstr(f_name,l_name,m_name,dept,desg,branch_no) values('prerana','agale','pandurang','wealth management','financial planning',1);
INSERT INTO emp_mstr(f_name,l_name,m_name,dept,desg,branch_no) values('nikhil','badgujar','ajay','customer service',' loan officers ',2);
INSERT INTO emp_mstr(f_name,l_name,m_name,dept,desg,branch_no) values('tanuja','bedse','parag','customer service','Tellers',3);
INSERT INTO emp_mstr(f_name,l_name,m_name,dept,desg,branch_no) values('john','wick','snow','wealth management','financial planning',4);
/*List the employee details along with branch names to which they belong*/
SELECT * FROM branch_mstr INNER JOIN emp_mstr on b_no=branch_no;

/*4. Create following Tables*/
CREATE TABLE cntc_dets(code_no INTEGER PRIMARY KEY,cntc_type VARCHAR(40),cntc_data varchar(13),FOREIGN KEY(code_no) REFERENCES emp_mstr(e_mpno));

INSERT INTO cntc_dets values(1,'lineline',9372510998);
INSERT INTO cntc_dets values(2,'telephone',9372510002);
INSERT INTO cntc_dets values(3,'lineline',9375610997);
INSERT INTO cntc_dets values(4,'telephone',9579510990);
/*List the employee details along with contact details using left outer join & right join*/
SELECT * FROM emp_mstr RIGHT JOIN cntc_dets on e_mpno=code_no;
SELECT * FROM emp_mstr LEFT OUTER JOIN cntc_dets on e_mpno=code_no;
/*
6. a) Create View on borrower table by selecting any two columns and perform insert update
delete operations
b) Create view on borrower and depositor table by selecting any one column from each table
perform insert update delete operations
c) create updateable view on borrower table by selecting any two columns and perform insert,
update and delete operations
*/

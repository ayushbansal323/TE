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
/*
CREATE TABLE cust_mstr(custno FORIGN,fname,lname)
CREATE TABLE acc_fd_cust_dets(codeno,acc_fd_no)
fd_dets(fd_sr_no,amt)
*/

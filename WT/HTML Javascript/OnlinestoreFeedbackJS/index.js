
 
var user=[{"user":"admin","pass":"admin"}];
var books=[{"book":"System programming","price":600,"quantity":3},
			{"book":"Computer Networks","price":850,"quantity":5},
			{"book":"mybook","price":150,"quantity":5},
			{"book":"Thinking in C++","price":850,"quantity":7}];
 function submit(){
	var name = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	var email = document.getElementById('email').value;
	var telnumber = document.getElementById('number').value;
	var gender = document.mainform.gender.value;
	// var city = document.getElementById('city').value;
	var agree = document.getElementById('agree').checked;
	//var gender = document.getElementById('gender').value;
	var address = document.getElementById('address').value;
	var button = document.getElementById('button').value;
	//alert("hi\t"+gender.length);
	var REtel=new RegExp("[0-9]{10}");
	var REpass=new RegExp("[a-zA-Z0-9!#$&]{6,10}");
	var REname=new RegExp("[a-zA-Z]+");
	var REemail=new RegExp("[a-zA-Z0-9]+@[a-zA-Z0-9]+[.][a-zA-Z]{2,3}");
	//alert("hi"+REtel.test(telnumber)+"\t"+REpass.test(password)+"\t"+REname.test(name)+"\t"+REemail.test(email)+"\t"+address.length);
	
	if (!REname.test(name)) {
		alert("Name not valid");
		document.getElementById('username').focus();
		return;
	}
	if (!REpass.test(password)) {
		alert("Password Not valid");
		document.getElementById('password').focus();
		return;
	}	
	if (!REtel.test(telnumber)) {
		alert("Enter valid number");
		document.getElementById('number').focus();
		return;
	}
	if (!REpass.test(password)) {
		alert("Password Not valid");
		document.getElementById('password').focus();
		return;
	}	
	if (!REemail.test(email)) {
		alert("Email Not correct");
		document.getElementById('email').focus();
		return;
	}
	if (address.length>100) {
		alert("reduce address length");
		document.getElementById('address').focus();
		return;
	}
	if (!gender.length) {
		alert("Please Select gender");
		document.getElementById('gender').focus();
		return;
	}
	if (!agree) {
		alert("Please Select Agree");
		document.getElementById('agree').focus();
		return;
	}
	//alert(gender);
	user.push({"name":name,"pass":password});
	window.location.href = "login.html";

}

function auth() {
	var u=document.getElementById("user").value;
	var p=document.getElementById("pass").value;
	for (var i = user.length - 1; i >= 0; i--) {

		if(user[i].user==u && user[i].pass==p)
		{
			window.location.href = "storelist.html";
		}
	}
	alert("Invalid username or password");
}

function price() {
	var sum=0;
	for (var i = 1; i <= 3; i++) {
		if(document.getElementById(i).checked)
		{
			sum=sum+parseInt(document.getElementById(i).value);
		}
	}
	
	alert("total price :"+sum);
	window.location.href="login.html";
}


function enqauth()
{
	fname= document.getElementById("fname").value;
	lname= document.getElementById("lname").value;
	subject= document.getElementById("subject").value;

	REfname=new RegExp("[a-zA-Z]+");
	RElname=new RegExp("[a-zA-Z]+");
	
	if(!REfname.test(fname))
	{
		alert("First name not correct");
		return;
	}
	if(!RElname.test(fname))
	{
		alert("Second name not correct");
		return;
	}
	if(subject.length>200)
	{
		alert("Subject should be less the 200 letters");
		return;
	}
	alert("Thankyou !!!");
	window.location.href="booklist.html";
}
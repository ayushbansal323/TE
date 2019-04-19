
 
var user=[{"user":"admin","pass":"admin"}];
var books=[{"book":"System programming","price":600,"quantity":3},
			{"book":"Computer Networks","price":850,"quantity":5},
			{"book":"mybook","price":150,"quantity":5},
			{"book":"Thinking in C++","price":850,"quantity":7}];
 function submit(){
	var name = document.getElementById('username').value;
	var lname= document.getElementById('lname').value;
	var email = document.getElementById('email').value;
	var telnumber = document.getElementById('number').value;
	var gender = document.mainform.gender.value;
	// var city = document.getElementById('city').value;
	//var gender = document.getElementById('gender').value;
	var address = document.getElementById('address').value;
	var button = document.getElementById('button').value;
	//alert("hi\t"+gender.length);
	var REtel=new RegExp("[0-9]{10}");
	var RElname=new RegExp("[a-zA-Z]+");
	var REname=new RegExp("[a-zA-Z]+");
	var REemail=new RegExp("[a-zA-Z0-9]+@[a-zA-Z0-9]+[.][a-zA-Z]{2,3}");
	//alert("hi"+REtel.test(telnumber)+"\t"+REpass.test(password)+"\t"+REname.test(name)+"\t"+REemail.test(email)+"\t"+address.length);
	
	if (!REname.test(name)) {
		alert("First Name not valid");
		document.getElementById('username').focus();
		return;
	}
	if (!RElname.test(lname)) {
		alert("Last name  Not valid");
		document.getElementById('lname').focus();
		return;
	}	
	if (!REtel.test(telnumber)) {
		alert("Enter valid number");
		document.getElementById('number').focus();
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
	//alert(gender);
	window.location.href = "next.html";

}

 function submit2(){
	var name = document.getElementById('username').value;
	var lname= document.getElementById('lname').value;
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
	var RElname=new RegExp("[a-zA-Z]+");
	var REname=new RegExp("[a-zA-Z]+");
	var REemail=new RegExp("[a-zA-Z0-9]+@[a-zA-Z0-9]+[.][a-zA-Z]{2,3}");
	//alert("hi"+REtel.test(telnumber)+"\t"+REpass.test(password)+"\t"+REname.test(name)+"\t"+REemail.test(email)+"\t"+address.length);
	
	if (!REname.test(name)) {
		alert("First Name not valid");
		document.getElementById('username').focus();
		return;
	}
	if (!RElname.test(lname)) {
		alert("Last name  Not valid");
		document.getElementById('lname').focus();
		return;
	}	
	if (!REtel.test(telnumber)) {
		alert("Enter valid number");
		document.getElementById('number').focus();
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
	alert("Thankyou !!");
	window.location.href = "login.html";

}
function auth() {
	var u=document.getElementById("user").value;
	var p=document.getElementById("pass").value;
	for (var i = user.length - 1; i >= 0; i--) {

		if(user[i].user==u && user[i].pass==p)
		{
			window.location.href = "details.html";
		}
	}
	alert("Invalid username or password");
}
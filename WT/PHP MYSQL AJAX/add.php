<?php 
$mysqli = mysqli_connect("localhost","root","","ass6");
$id=$_POST["id"];
$name=$_POST["name"];
$age=$_POST["age"];
$city=$_POST["city"];
$type=$_POST["type"];

if ($type=="create") {
	$result = mysqli_query($mysqli,"INSERT INTO student VALUES('$id','$name','$age','$city')");
	echo $result;
}
elseif ($type=="search") {
	$result = mysqli_query($mysqli,"SELECT * FROM student WHERE id='$id'");
	while($row=mysqli_fetch_assoc($result))
	{
		echo $row["id"]."<br>".$row["name"]."<br>".$row["age"];
	}
}
elseif ($type=="update") {
	$result = mysqli_query($mysqli,"UPDATE student SET name='$name',age='$age',city='$city' WHERE id='$id'");
	echo $result;
}
elseif ($type=="delete") {
	$result = mysqli_query($mysqli,"DELETE FROM student where id='$id'");
	echo $result;
}

?>
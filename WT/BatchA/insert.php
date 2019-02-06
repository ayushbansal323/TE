<?
	include_once("config.php");
	echo "<b>WELCOME</b><br>";
	$name = $_POST["username"];
	$age = $_POST["userage"];
	$city = $_POST["usercity"] ;
	$result = mysqli_query($mysql,"insert into emp1(name,age,city) values('$name','$age','$city')");
	if($result == True)
	{
		echo "Hello ".$_POST["username"]." Your age is ".$_POST["userage"]." and You live in ".$_POST["usercity"]." Having mail id ",$_POST["useremail"]."<br>";
	}
	else
	{
		echo "Error";
	}
?>


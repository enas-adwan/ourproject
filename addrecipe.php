<?php
if($_POST['secret'] != '3CH6knCsenas2va8GrHk4mf3JqmUctCM') {


    exit("Access denied");
}
$number = $_POST['number'];

$password = $_POST['password'];


if($number == '' ||  $password == '' ){
echo 'please fill all values';
}else{


$con=mysqli_connect("localhost","root","","project");

$sql = "SELECT * FROM project WHERE numberid='$number' ";

$check = mysqli_fetch_array(mysqli_query($con,$sql));

if(isset($check)){
echo 'username or email already exist';
}else{
$sql = "INSERT INTO project (numberid,password) VALUES('$number','$password')";
if(mysqli_query($con,$sql)){
echo 'successfully registered';
}else{
echo 'oops! Please try again!';
}
}
mysqli_close($con);
}
?>

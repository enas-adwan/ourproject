<?php
if($_POST['secret'] != '3CH6knCsenas2va8GrHk4mf3JqmUctCM') {


  exit("Access denied");
}
$title = $_POST['title'];

$calory = $_POST['calory'];
$desc = $_POST['desc'];
$list = $_POST['list'];
$total = $_POST['total'];
$prep = $_POST['prep'];
$cook = $_POST['cook'];


if($title == '' ||  $calory == ''|| $desc == ''||$list=='' ||$prep==''||$cook==''){
echo 'please fill all values';
}else{

 

$con=mysqli_connect("localhost","root","","project");

$sql = "SELECT * FROM recipe WHERE title='$title' ";

$check = mysqli_fetch_array(mysqli_query($con,$sql));

if(isset($check)){
echo 'title already exist';
}else{
$sql = "INSERT INTO recipe (title,calory,descc,list,prep,cook,total) VALUES('$title','$calory','$desc','$list','$prep','$cook','$total')";
if(!$sql){
echo "err";
}
if(mysqli_query($con,$sql)){
echo 'successfully added';
}else{
echo 'oops! Please try again!';
}
}
mysqli_close($con);
}
?>

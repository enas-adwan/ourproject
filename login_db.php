<?php 
require "conn.php";
$number = $_POST["number"];
$user_pass = $_POST["password"];
$mysql_qry = "select * from project where number like '$number' and password like '$user_pass';";
$result = mysqli_query($conn ,$mysql_qry);
if(mysqli_num_rows($result) > 0) {
echo "login success !!!!! Welcome user";
}
else {
echo "login not success";
}

?>
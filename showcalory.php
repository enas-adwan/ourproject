<?php 
require "conn.php";
$calory = $_POST["calory"];


  $sql = "select * from calory";
 
  $res = mysqli_query($con,$sql);

  while($row = mysqli_fetch_array($res)){
    array_push($result,
    array('num'=>$row[0],
    
  ));
}

?>
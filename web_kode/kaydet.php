<?php
  
  $file = fopen("ocak.json", "w") or die("can't open file");  

  date_default_timezone_set('Europe/Istanbul');

  $status = $_GET['status'];

  $ocak1 = $_GET['ocak1'];
  $ocak2 = $_GET['ocak2'];
  $ocak3 = $_GET['ocak3'];
  $ocak4 = $_GET['ocak4'];

  $start = $_GET['start'];
  $stop = $_GET['stop'];

  $clock = time() + 10800;

  $start_parsed = explode(":",$start);
  $stop_parsed = explode(":",$stop);

  $working_time = $stop_parsed[0] * 3600;
  $working_time = $working_time + $stop_parsed[1] * 60;

  $unix_start = strtotime(date("Y-m-d ".$start.":00")) + 10800;

  $unix_stop = $unix_start + $working_time;

  fwrite($file, '{');

  fwrite($file, '"status":"'.$status.'",');

  fwrite($file, '"clock":"'.$clock.'",');
  
  fwrite($file, '"time":');
  fwrite($file, '{');
  fwrite($file, '"start":"'.$start.'",');
  fwrite($file, '"stop":"'.$stop.'"');
  fwrite($file, '},');

  fwrite($file, '"unix":');
  fwrite($file, '{');
  fwrite($file, '"start":"'.$unix_start.'",');
  fwrite($file, '"stop":"'.$unix_stop.'"');
  fwrite($file, '},');


  fwrite($file, '"ocak":');
  fwrite($file, '[');
  fwrite($file, $ocak1.",".$ocak2.",".$ocak3.",".$ocak4);
  fwrite($file, ']');


  fwrite($file, '}');

  fclose($file);

?>  
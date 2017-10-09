<?php

  $file = fopen("test.json", "w") or die("can't open file");  

  date_default_timezone_set('Europe/Istanbul');

fwrite($file,"");

  $url = 'http://www.espakilliev.site88.net/tou_robotik/ocak.json';
  $content = file_get_contents($url);
  $json = json_decode($content, true);

  $url2 = 'http://www.espakilliev.site88.net/tou_robotik/unix.json';
  $content2 = file_get_contents($url2);
  $json2 = json_decode($content2, true);

  $json['clock'] = $json2['clock'];

  $content = json_encode($json);

  fwrite($file,$content);

fclose($file);

$page = $_SERVER['PHP_SELF'];
$sec = "1";

?>  	

<html>
    <head>
    <meta http-equiv="refresh" content="<?php echo $sec?>;URL='<?php echo $page?>'">
    </head>
    <body>

    </body>
</html>					
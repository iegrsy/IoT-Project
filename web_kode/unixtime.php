  <?php

  $file = fopen("unix.json", "w") or die("can't open file");

  date_default_timezone_set('Europe/Istanbul');

  $clock = time() + 10800;

  fwrite($file, '{');

  fwrite($file, '"clock":"'.$clock.'"');

  fwrite($file, '}');

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


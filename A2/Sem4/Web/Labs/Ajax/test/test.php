<?php
    phpinfo();
    echo "HELLO WORLD\n";
    $db_path = "sqlite:C:/Users/aerap/Desktop/Web/Labs/Ajax/db.sqllite3";
    $db = new PDO($db_path) or die("cannot open db");
    echo "bd opened\n";
    $query = "SELECT * FROM pb1";
    foreach ($db->query($query) as $row)
    {
        echo $row[0]." ".$row[1]."\n";
    }
    $db = null;

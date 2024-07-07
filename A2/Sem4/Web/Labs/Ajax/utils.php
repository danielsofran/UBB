<?php
    function connect()
    {
        $db_path = "sqlite:C:/Users/aerap/Desktop/Web/Labs/Ajax/db.sqllite3";
        $db = new PDO($db_path) or die("cannot open db");
        return $db;
    }
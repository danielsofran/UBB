<?php
    include_once '../utils.php';
    $db = connect();
    # get the plecare value for GET
    $plecare = $_GET['plecare'];
    $query = "SELECT distinct sosire FROM pb1 where plecare = '$plecare'";
    $result = $db->query($query);
    $rows = array();
    while ($row = $result->fetch(PDO::FETCH_ASSOC)) {
        echo "<option value='" . $row['sosire'] . "'>" . $row['sosire'] . "</option>\n";
    }
    $db = null;

<?php
    include_once '../utils.php';
    $db = connect();
    $query = "SELECT distinct plecare FROM pb1";
    $result = $db->query($query);
    $rows = array();
    while ($row = $result->fetch(PDO::FETCH_ASSOC)) {
        echo "<option value='" . $row['plecare'] . "'>" . $row['plecare'] . "</option>\n";
    }
    $db = null;

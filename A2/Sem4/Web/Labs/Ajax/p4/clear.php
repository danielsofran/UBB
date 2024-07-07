<?php
    require_once '../utils.php';
    $db = connect();
    $sql = "DELETE FROM `mutariXO`";
    $result = $db->exec($sql);
    echo json_encode(array('success' => true));

<?php
    require_once '../utils.php'; // The mysql database connection script
    $db = connect();
    $query="SELECT id FROM pb2";
    $result = $db->query($query)->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($result);

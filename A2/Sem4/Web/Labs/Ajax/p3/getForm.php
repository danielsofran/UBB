<?php
    require_once '../utils.php'; // The mysql database connection script
    $db = connect();
    $id = $_GET['id'];
    $query="SELECT * FROM pb2 WHERE id = $id";
    $result = $db->query($query)->fetch(PDO::FETCH_ASSOC);
    echo json_encode($result);

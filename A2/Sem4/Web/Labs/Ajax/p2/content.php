<?php
    include_once("../utils.php");
    $db = connect();

    $page = $_GET["page"];
    $size = $_GET["size"];
    $page = $page * $size;
    $sql = "SELECT * FROM pb2 LIMIT $page, $size";
    $result = $db->query($sql);
    # JSON-encode the response
    $result = $result->fetchAll(PDO::FETCH_ASSOC);

    $sql = "SELECT COUNT(*) FROM pb2";
    $count = $db->query($sql);
    $count = $count->fetch(PDO::FETCH_ASSOC);
    $allow_next = 0;
    if($count["COUNT(*)"] > $page+$size)
        $allow_next = 1;

    echo '{"data":'.json_encode($result).', "next":'.($allow_next).'}';

<?php
    require_once "../../utils.php";
    $db = connect();
    $sql = "SELECT DISTINCT producator FROM pb6";
    $producatori = $db->query($sql)->fetchAll();
    $sql = "SELECT DISTINCT procesor FROM pb6";
    $procesoare = $db->query($sql)->fetchAll();
    $sql = "SELECT DISTINCT memorie FROM pb6";
    $memorii = $db->query($sql)->fetchAll();

    $filters = array(
        "producatori" => $producatori,
        "procesoare" => $procesoare,
        "memorii" => $memorii
    );
    echo json_encode($filters);
    $db = null;

<?php
    require_once "../../utils.php";
    $db = connect();
//    $data = json_decode(file_get_contents("php://input"));
    $producatori = $_GET["producatori"];
    $procesoare = $_GET["procesoare"];
    $memorii = $_GET["memorii"];

    if($producatori == "") $producatori = array();
    if($procesoare == "") $procesoare = array();
    if($memorii == "") $memorii = array();

//    echo implode(", ", $producatori)."\n";
//    echo implode(", ", $procesoare)."\n";
//    echo implode(", ", $memorii)."\n";

    $sql = "SELECT * FROM pb6 WHERE ";
    if(count($producatori) > 0) {
        $sql .= "producator IN (";
        foreach($producatori as $producator) {
            $sql .= "'".$producator."',";
        }
        $sql = substr($sql, 0, -1);
        $sql .= ")";
        if(count($memorii) > 0 || count($procesoare) > 0) {
            $sql .= " AND ";
        }
    }
    if(count($procesoare) > 0) {
        $sql .= "procesor IN (";
        foreach($procesoare as $procesor) {
            $sql .= "'".$procesor."',";
        }
        $sql = substr($sql, 0, -1);
        $sql .= ")";
        if(count($memorii) > 0) {
            $sql .= " AND ";
        }
    }
    if(count($memorii) > 0) {
        $sql .= "memorie IN (";
        foreach($memorii as $memorie) {
            $sql .= "'".$memorie."',";
        }
        $sql = substr($sql, 0, -1);
        $sql .= ")";
    }

    $content = $db->query($sql)->fetchAll();
    echo json_encode($content);
    $db = null;

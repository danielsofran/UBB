<?php
    require_once '../utils.php'; // The mysql database connection script
    $db = connect();
    $data = json_decode(file_get_contents("php://input"));
    $id = $data->id;
    $nume = $data->nume;
    $prenume = $data->prenume;
    $telefon = $data->telefon;
    $email = $data->email;
    $query="UPDATE pb2 SET nume='$nume', prenume='$prenume', telefon='$telefon', email='$email' WHERE id=$id";
    $db->exec($query);
    $db = null;
    echo '{"status":"ok"}';
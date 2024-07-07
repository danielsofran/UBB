<?php
    require_once '../utils.php';
    $db = connect();

    function checkWin($db, $player) {
        $sql = "SELECT `index` FROM `mutariXO` WHERE `player` = '$player'";
        $result = $db->query($sql);
        $result = $result->fetchAll();
        $winnerMoves = [
            ["11", "12", "13"],
            ["21", "22", "23"],
            ["31", "32", "33"],
            ["11", "21", "31"],
            ["12", "22", "32"],
            ["13", "23", "33"],
            ["11", "22", "33"],
            ["13", "22", "31"]
        ];
        foreach ($winnerMoves as $moves) {
            $count = 0;
            foreach ($moves as $move) {
                foreach ($result as $row) {
                    if ($row['index'] == $move) {
                        $count++;
                    }
                }
            }
            if ($count == 3) {
                return true;
            }
        }
        return false;
    }


//    $player = "O";
//    checkWin($db, $player);
    $player = $_GET['player'];
    $index = $_GET['index'];
    $sql = "INSERT INTO `mutariXO` (`player`, `index`) VALUES ('$player', '$index')";
    $result = $db->exec($sql);
    if(checkWin($db, $player)) {
        echo json_encode(array('win' => "$player"));
    } else {
        echo json_encode(array('win' => false));
    }

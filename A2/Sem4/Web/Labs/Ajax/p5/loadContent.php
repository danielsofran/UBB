<?php
    $path = $_GET['path'];
//    print $path;
//    $path = "C:\Users\aerap\Desktop\Web\Labs\Ajax\p1";
    if(!is_dir($path)) {
//        print $path;
        # load the file
        echo file_get_contents($path);
        return;
    }
    $content = scandir($path);
    foreach ($content as $element) {
        if($element != "." && $element != "..") {
            $finalPath = $path . "\\" . $element;
            $finalPath = str_replace("\\", "\\\\", $finalPath);

            if(is_dir($path . "\\" . $element))
                echo("<li><p onclick='loadDir(this.parentNode, \"$finalPath\")'>$element</li>\n");
            else
                echo("<li onclick='loadFile(\"$finalPath\")'>$element</li>\n");
        }
    }


$file = "Main"
$rulari = 10

# Compilare class Java
Write-Host "Compilare class Java"
cd "src"
javac "$file.java"

# Executare class Java

$suma = 0

for ($i = 0; $i -lt $rulari; $i++){
    Write-Host "Rulare" ($i+1)
    $a = java $file
    Write-Host $a
    $suma += $a
    Write-Host ""
}
$media = $suma / $rulari
#Write-Host $suma
Write-Host "Timp de executie mediu:" $media

# Creare fisier .csv
if (!(Test-Path outJ.csv)){
    New-Item outJ.csv -ItemType File
    #Scrie date in csv
    Set-Content outJ.csv 'Timp executie'
}

# Append
Add-Content outJ.csv "$($media)"

cd ..

[Console]::Beep(1000, 800)
[System.GC]::Collect()
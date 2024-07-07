$file = "PrCpp.exe" # Nume fisier exe
$rulari = 10

# Executare exe in cmd mode
cd "x64\Debug"

$suma = 0

for ($i = 0; $i -lt $rulari; $i++){
    Write-Host "Rulare" ($i+1)
    $a = (cmd /c .\$file 2`>`&1)
    Write-Host $a
    $suma += $a
    Write-Host ""
}
$media = $suma / $i
#Write-Host $suma
Write-Host "Timp de executie mediu:" $media

# Creare fisier .csv
if (!(Test-Path outC.csv)){
    New-Item outC.csv -ItemType File
    #Scrie date in csv
    Set-Content outC.csv 'Timp executie'
}

# Append
Add-Content outC.csv "$($media)"

cd ../..

# [Console]::beep(1000, 800)
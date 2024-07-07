bits 32
global start
extern exit, printf
import exit msvcrt.dll
import printf msvcrt.dll

segment data use32 class=data
    s db 'a', 'A', 'b', 'B', '2', '%', 'x'
    l equ $-s
    d resb l

segment code use32 class=cod
start:
    mov esi, s  ; adresa lui s
    mov edi, 0  ; lungimea sirului rezultat
    
    repeta:
        cmp byte [esi], 'a'
        jb continue         ; sarim peste caracterele dinaintea lui 'a'
        
        cmp byte [esi], 'z'
        ja continue         ; sarim peste caracterele de dupa 'a'
        
        ; in cazul in care caracterul este o litera mica
        mov al, byte [esi]
        mov byte [d+edi], al    ; il adaugam in d
        inc edi                 ; crestem lungimea sa
        
        continue:               ; eticheta corespunzatoare trecerii la pasul urmator
        inc esi                 ; adresa va indica acum urmatoarea locatie de memorie
        cmp esi, s+l            ; daca aceasta apartine intervalului [s+0, s+l)
    jb repeta                   ; bucla continua
    
    push dword 0
    call [exit]
    
    
    
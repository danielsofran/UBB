bits 32

global start

extern exit, printf, scanf, fclose, fread
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll
import fclose msvcrt.dll
import fread msvcrt.dll

%macro CIFRA_IMPARA 1
    clc
    push bx
    mov bl, %1
    test bl, 1
    jz .next
    cmp bl, '0'
    jb .next
    cmp bl, '9'
    ja .next
    stc
    .next:
    pop bx
%endmacro


segment data use32 class=data
    filename db "input.txt", 0  ; numele fisierului
    mode db "r", 0              ; modul de acces la fisier
    descriptor dd 0             ; descriptorul fisierului
    cifre resb 101              ; sirul de cifre din fisier, stocat ca sir de caractere
    cmax db 0                   ; cifra cu valoarea maxima
    chr db 0                    ; un caracter citit
    format db "%s", 10, 0 ; formatul de afisare
    format2 db "%[^\n]", 0

segment code use32 class=code
    start:
        push cifre
        push format2
        call [scanf]
        add esp, 8
        
        push cifre
        push format
        call [printf]
        add esp, 8
        
        final: ; exit(0);
        push    dword 0     
        call    [exit]      

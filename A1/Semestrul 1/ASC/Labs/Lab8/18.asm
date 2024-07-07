bits 32

global start
extern exit, printf, scanf
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll

segment data use32 class=data
    n1 dd 0
    n2 dd 0
    format_b10 db "%d", 0 ; numarul in baza 10, CU SEMN     (nu se specifica)
    format_b16 db "%X", 0 ; numarul in baza 16, UPPERCASE   (nu se specifica)
    mesaj_b10 db "Introduceti un numar in baza 10: ", 0
    mesaj_b16 db "Introduceti un numar in baza 16(folosind litere mari): ", 0
    mesaj_rez db "Numarul de biti ai sumei dintre cele doua numere introduse este: %d", 0
    

; citim 2 nr si afisam nr de biti ai sumei
segment code use32 class=code
start:
    ; afisam mesajul introductiv
    push mesaj_b10
    call [printf]
    add esp, 4
    
    ; citim primul numar
    push n1
    push format_b10
    call [scanf]
    add esp, 4*2
    
    ; afisam mesajul introductiv
    push mesaj_b16
    call [printf]
    add esp, 4
    
    ; citim al doilea numar
    push n2
    push format_b16
    call [scanf]
    add esp, 4*2
    
    ; calculam suma
    mov eax, [n1]
    add eax, [n2]
    
    mov edx, 0 ; numarul de biti de 1
    mov ecx, 32
    biti:   ; parcurge bitii din eax
        ; clc ; stergem CF, pentru a nu avea biti de 1 adaugati in fata nr
        ror eax, 1    ; mutam ultimul bit in cf
        jnc continue  ; daca CF = 0, continuam
        inc edx       ; altfel, incrementam rezultatul
        continue:
    loop biti
    
    ; afisam rezultatul
    push edx
    push mesaj_rez
    call [printf]
    add esp, 4*2
    
    
    
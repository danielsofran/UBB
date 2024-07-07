bits 32
global start

extern reverse

extern exit, printf, fscanf, fopen, fclose, system
import exit msvcrt.dll
import fscanf msvcrt.dll
import printf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import system msvcrt.dll

segment data use32 class=data
    filename db "numere.txt", 0
    descriptor dd 0
    mod db "r", 0
    format_input db "%d", 0
    format_output db "%d ", 0
    lungime dd 0
    vector resd 101 ; maximul de numere
    numar dd 0
    cmd db "pause", 0
    

segment code use32 class=code
start:
    push mod
    push filename
    call [fopen]
    add esp, 4
    mov [descriptor], eax
    cmp eax, 0
    je final
    
    ; citire numere
    numere:
        ; citire numar
        push numar
        push format_input
        push dword[descriptor]
        call [fscanf]
        add esp, 4*3
        
        cmp eax, 1
        jne prelucrare
        
        ; dublam numarul
        mov eax, [numar]
        sal eax, 1 ; eax = eax * 2^1
        
        ; adaugam elementul la finalul vectorului
        mov ecx, [lungime]
        mov [4*ecx+vector], eax
        
        inc dword [lungime]
        
        jmp numere ; continuam pt urm nr
        
    prelucrare:
        push dword[lungime]
        push vector
        call reverse
        add esp, 4*2
    
    mov ecx, [lungime]
    mov ebx, 0
    afisare:
        push ecx
            ; afisam intotdeauna ultimul element
            push dword[vector+4*ebx]
            push format_output
            call [printf]
            add esp, 4*2
        pop ecx
        inc ebx
        ; dec ecx
        ; cmp ecx, 0
    loop afisare
    
    final: ; close the file
    push dword[descriptor]
    call [fclose]
    add esp, 4
    
    push cmd
    call [system]
    add esp, 4
    
    push 0
    call [exit]

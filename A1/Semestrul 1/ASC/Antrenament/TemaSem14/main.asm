bits 32

global start

extern exit, printf, scanf, fopen, fclose, fscanf
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fscanf msvcrt.dll


segment data use32 class=data
    fisier resb 50
    numar dd 0
    contor dd 0
    descriptor dd 0
    formats db "%s", 0 
    formatd db "%d", 0
    mod db "r", 0

segment code use32 class=code
    octprim:
        mov al, [esp+6]
        cmp al, 2
        jb neprim
        je prim
        mov ah, 0
        mov dl, 2
        .bucla:
            div dl
            cmp ah, 0
            je neprim
            inc dl
            mov ah, 0
            mov al, [esp+6]
            cmp dl, [esp+6]
            je prim
            
            jmp .bucla
        prim: mov eax, 1
                ret
        neprim: mov eax, 0
                ret
    start:
        push fisier
        push formats
        call [scanf]
        add esp, 4*2
        
        push mod
        push fisier
        call [fopen]
        add esp, 4*2
        
        cmp eax, 0
        je _exit
        mov [descriptor], eax
        

        bucla:
            push numar
            push formatd
            push dword[descriptor]
            call [fscanf]
            add esp, 4*3
            
            cmp eax, 1
            jne afisare
            
            push dword[numar]
            call octprim
            add esp, 4
            cmp eax, 0
            je bucla
            inc dword[contor]
            jmp bucla
        
        afisare:
            push dword[contor]
            push formatd
            call [printf]
            add esp, 4*2
            
            push dword[descriptor]
            call [fclose]
            add esp, 4
            
        _exit:
        push    dword 0     
        call    [exit]      

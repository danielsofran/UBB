bits 32

global start

extern exit, printf, scanf, fopen, fclose, fprintf, fscanf
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll
import fscanf msvcrt.dll


segment data use32 class=data
    formats db "%s", 0
    formatd db "%x", 0
    mod db "w", 0
    n dd 0
    cv resb 21
    sir db "ana", 0

segment code use32 class=code
    isok:
        mov ecx, 0
        mov esi, [esp+4]
        lodsb
        mov dl, al
        .bucla:
            cmp al, 0
            je .next
            
            cmp al, '#'
            je .next
            
            mov dh, al
            inc ecx
            lodsb
            jmp .bucla
        .next:
            cmp dl, dh
            jne .zero
            
            cmp ecx, [n]
            jl .zero
            
            mov eax, 1
            ret
            
            .zero:
            mov eax, 0
            ret
        
    start:
        ; push sir
        ; call isok
        ; add esp, 4
        
        ; mov eax, 0
        ; mov al, -512<<2
        
        movsx ax, al
        
        push 00020001h
        mov eax, 0
        pop ax
        
        push eax
        push formatd
        call [printf]
        add esp, 4*2
        push    dword 0     
        call    [exit]      

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

%macro AFISARE 1
pushad
push %1
push formatd
call [printf]
add esp, 4*2
popad
%endmacro

%macro CITIRE 1
pushad
push %1
push formatd
call [scanf]
add esp, 4*2
popad
%endmacro

segment data use32 class=data
    n dd 0
    v resd 100
    rez resb 101
    formatd db "%d", 0
    sum dd 0

segment code use32 class=code
    start:
        CITIRE n
        mov ecx, [n]
        mov edi, v
        cld
        citire:
            CITIRE EDI
            add edi, 4
        loop citire
        
        mov esi, v
        mov edi, rez
        mov ecx, [n]
        bucla:
            lodsd
            mov dx, 0
            mov bx, 10
            mov dword[sum], 0
            cifre:
                div bx
                test dx, 1
                jnz next
                add word[sum], dx
                next:
                mov dx, 0
                cmp ax, 0
                je continue
                jmp cifre
            continue:
                mov al, [sum]
                stosb
        loop bucla
        
        mov eax, 0
        mov ecx, [n]
        mov esi, rez
        _loop:
            lodsb
            AFISARE eax
        loop _loop
        push    dword 0     
        call    [exit]      

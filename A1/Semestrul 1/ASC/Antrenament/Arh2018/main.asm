bits 32

global start, formatd, formats, formatx

extern maxbyte

extern exit, printf, scanf, fopen, fclose, fprintf, fscanf, system
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll
import fscanf msvcrt.dll
import system msvcrt.dll

%macro PRINTF 2
pushad
push %2
push %1
call [printf]
add esp, 4*2
popad
%endmacro

segment data use32 class=data
    sir dd 1234A678h, 12345678h, 1ac3b47dh, 0fedc9876h
    len equ ($-sir)/4
    poz resb len+1
    sum dd 0
    formatx db "%x ", 10, 0
    formatd db "%d ", 10, 0
    formats db "%s ", 10, 0
    nr dd 0
    i dd 0
    
    cmd db "pause", 0

segment code use32 class=code
    start:
        mov esi, sir
        mov edi, poz
        mov ecx, len
        bucla:
            pushad
            push i
            push sum
            push esi
            call maxbyte
            add esp, 4*3
            mov [nr], eax
            popad
            mov al, [i]
            add al, '0'
            stosb
            PRINTF formatx, dword[nr]
            add esi, 4
        loop bucla
        
        mov al, 0
        stosb
        PRINTF formats, poz
        mov al, [sum]
        cbw
        cwde
        PRINTF formatd, eax
        push cmd
        call [system]
        push    dword 0    
        call    [exit]      

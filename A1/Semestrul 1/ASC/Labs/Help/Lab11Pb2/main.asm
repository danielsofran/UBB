bits 32
global start

extern conversie

extern exit, printf, scanf, system
import exit msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll
import system msvcrt.dll

segment data use32 class=data
    format_cif db "%s", 0
    format_d db "%d", 0
    input db "Introduceti nr de elemente: ", 0
    n dd 0
    sir resb 101
    v resd 100
    cmd db "pause", 0

segment code use32 class=code
start:
    ; citim nr de elem
    push input
    call [printf]
    add esp, 4
    
    push n
    push format_d
    call [scanf]
    add esp, 4*2
    
    mov ecx, [n]
    mov edi, v
    citire:
    push ecx
        push sir
        push format_cif
        call [scanf]
        add esp, 4*2
        
        push sir
        call conversie ; stdcall
        stosd
        
        push eax
        push format_d
        call [printf]
        add esp, 4*2
    pop ecx
    loop citire
    
    push cmd
    call [system]
    add esp, 4
    
    push 0
    call [exit]

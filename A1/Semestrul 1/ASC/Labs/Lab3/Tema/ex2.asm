bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    a db               11h
    b dw             1111h
    c dd        0EEEEEEEFh
    d dq 1111111111111111h

; c+d-a-b+(c-a) CU SEMN
segment code use32 class=code
start:
    mov eax, [c]    ; eax = c
    add [d], eax
    adc [d+4], dword 0 ; d = d + c = 1111 1112 0000 0000
    
    mov al, [a]
    cbw
    cwde            ; eax = a
    sub dword [d], eax    
    sbb dword [d+4], dword 0  ; d = d + c - a = 1111 1111 FFFF FFEF
    
    mov ax, [b]
    cwde            ; eax = b
    sub [d], eax
    sbb [d+4], dword 0 ; d = d + c - a - b = 1111 1111 FFFF EEDE
    
    mov al, [a]
    cbw
    cwde            ; eax = a
    mov ebx, eax    ; ebx = a
    mov eax, [c]    ; eax = c
    sub eax, ebx    ; eax = c - a = EEEE EEDE
    sbb [d+4], dword 0 ; in caz de underflow
    
    add [d], eax
    adc [d+4], dword 0 ; d = d + c - a - b + (c-a) = 1111 1112 EEEE DDBC
    
    mov eax, dword [d]  ; eax = EEEE DDBC
    mov edx, dword [d+4]; edx = 1111 1112
    
    push dword 0
    call [exit]
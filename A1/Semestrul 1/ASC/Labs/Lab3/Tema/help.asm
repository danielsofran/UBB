bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    ; d dq 0FEDCBA9876543210h
    ; ; dword [d] = 76543210h
    ; ; dword [d+4] = FEDCBA98h
    ; ; word [d] = 3210
    ; ; word [d+2] = 7654
    ; ; ...
    ; ; byte [d+4] = 98
    
    d dq 01111111181111111h
    
segment code use32 class=code
start:
    ; ; explicatie adc
    ; mov ah, 0
    ; mov al, 0AFh
    ; mov bl, 0A0h
    ; add al, bl ; A0 + AF = 14F = BYTE !!! => al = al+bl = -||- = 4F
    ; adc ah, 0 ; AX = AH (=0+CF) + AL(byte[al+bl])
    ; ; adc a, b ; a = a + b + CF
    
    ; d + d
    ; add var, x-> mem64 -> IMPOSIBIL PE 32 DE BITI!!
    ; => add dword[var], reg32_1
    ;    add dword[var+4], reg32_2 ?!?!
    
    ; d -> EDX:EAX
    mov eax, dword[d]
    mov edx, dword[d+4]
    ; V1
    add [d], eax
    adc [d+4], edx
    ; edx:eax = 2*d
    mov eax, dword[d]
    mov edx, dword[d+4]
    
    push dword 0
    call [exit]
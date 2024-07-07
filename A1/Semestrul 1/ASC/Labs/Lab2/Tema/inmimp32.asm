bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    e dw 4
    f dw 5
    g dw 9
    rez resd 1

segment code use32 class=code
start:
    mov ax, [e]
    add ax, [f]
    mul WORD [g] ; rez = DX:AX = 9*9 = 81 = 51(16)
    
    push dword 0
    call [exit]
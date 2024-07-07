bits 32

global start

extern exit
import exit msvcrt.dll

segment data use32 class=data
    a DB 1
    b DB 2
    c DB 3
    rez RESB 1
    
segment code use32 class=code
start:
    MOV AX, [a]
    ADD AX, [b]
    SUB AX, [c]
    SUB AX, 100
    MOV [rez], AX
    push dword 0
    call [exit]
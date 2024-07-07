bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    a dw 1
    b dw 2
    c dw 4
    d dw 6
    ; result 5
segment clde use32 class=code
start:
    mov ax, [b]
    add ax, [b]
    add ax, [d]
    mov cx, [c]
    add cx, [a]
    sub ax, cx
    push dword 0
    call [exit]
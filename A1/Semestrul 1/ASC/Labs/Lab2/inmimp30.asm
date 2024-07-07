bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    e dw 25
    h dw 25
    a db 10

segment code use32 class=code
start:
    mov al, 3
    mul BYTE [a]
    neg ax
    add ax, [e]
    add ax, [h]
    mov bx, ax
    mov ax, 100
    mov dx, 0
    div bx ; ax = rez
    push dword 0
    call [exit]
    
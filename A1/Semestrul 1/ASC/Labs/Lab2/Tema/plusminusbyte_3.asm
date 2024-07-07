bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    a db 3
    b db 4
    c db 5
    d db 100
    ; result is (c+d)-(a+d)+b = c-a+b = 6
segment code use32 class=code
start:
    mov al, [c]
    add al, [d]
    mov ah, [a]
    add ah, [d]
    sub al, ah
    add al, [b]
    push dword 0
    call [exit]
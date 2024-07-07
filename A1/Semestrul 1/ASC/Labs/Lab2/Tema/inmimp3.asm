bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    a db 2
    b db 1
    c db 0
    d dw 15
    ; (-1+15-2*2)/2 = 10/2 = 5
segment code use32 class=code
start:
    mov al, 2
    inc BYTE [b] ; b+1
    mul BYTE [b] ; AX = AL * (b+1) = 2*2 = 4
    neg ax       ; -2*(b+1)
    add ax, [d]
    dec ax       ; -1+...
    div BYTE [a] ; al = rezultat
    push dword 0
    call [exit]
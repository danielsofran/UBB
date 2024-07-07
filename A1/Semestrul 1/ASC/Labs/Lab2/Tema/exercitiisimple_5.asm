bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    a dw 10
    b db 4 
segment code use32 class=code
start:
    
    ; mov ax, [a] ; ax = 1010 = A
    ; div BYTE [b] ; al = ax / 4
    ; mov bl, al
    
    mov cl, 4
    mov ax, 10
    div cl
    mov bl, al
    
    push dword 0
    call [exit] 
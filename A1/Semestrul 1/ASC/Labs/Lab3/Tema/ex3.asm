bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    a dw 1
    b db 44
    c dw 29
    x dq 100
    i resd 1 ; impartitor

;x-(a*100+b)/(b+c-1); FARA SEMN
segment code use32 class=code
start:
    mov ax, [a]     ; ax = a
    mov bx, 100
    mul bx          ; dx:ax = a*100 = 100
    mov bl, [b]
    mov bh, 0       ; bx = b
    add ax, bx
    adc dx, word 0  ; dx:ax = a*100 + b = 144
    push dx
    push ax
    pop eax         ; eax = a*100 + b
    mov edx, dword 0; edx:eax = a*100 + b
    
    mov dword [i], 0
    mov bx, [c]
    add word [i], bx    ; i = c
    ;adc word [i+2], word 0 - imposibil
    mov bl, [b]
    mov bh, 0
    add word [i], bx    
    adc word [i+2], 0   ; i = c + b
    dec dword [i]       ; i = c + b - 1
    ; dec byte [b]    ; b - 1
    ; mov bl, [b]
    ; mov bh, 0       ; bx = b - 1
    ; add [c], bx     ; c = c + b - 1
    
    div dword [i]    ; eax = (a*100+b)/(b+c-1) dx = (a*100+b)%(b+c-1)
    
    sub [x], eax
    sbb [x+4], dword 0 ; x = x - (a*100+b)/(b+c-1)
    
    mov eax, [x]
    mov edx, [x+4]      ; edx:eax = x
    
    push dword 0
    call [exit]
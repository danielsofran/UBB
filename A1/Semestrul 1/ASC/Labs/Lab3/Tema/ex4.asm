bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    a dw 1
    b db 44
    c dw 29
    x dq 100
    d resd 1 ; deimpartit - nu depaseste dword
    i dd 0 ; impartitor

;x-(a*100+b)/(b+c-1); CU SEMN
segment code use32 class=code
start:
    mov ax, [a]     ; ax = a
    mov bx, 100
    imul bx          ; dx:ax = a*100 = 100
    push dx
    push ax
    pop dword [d]   ; d = dx:ax
    mov al, [b]
    cbw
    cwde            ; eax = b
    add [d], eax    ; d = a*100+b
    mov eax, [d]    ; eax = d
    cdq             ; edx:eax = d
    mov ecx, eax    ; edx:ecx = d
    
    mov ax, [c]     ; ax = c
    cwde            ; eax = c
    add [i], eax    ; i = c
    mov al, [b]     ; al = b
    cbw             ; ax = b
    cwde            ; eax = b
    add [i], eax    ; i = c + b
    dec dword [i]   ; i = c + b - 1
    
    mov eax, ecx    ; edx:eax = d
    idiv dword [i]   ; eax = d/i edx = d%i

    sub [x], eax   
    sbb [x+4], dword 0 ; x = x - d/i
    
    mov eax, [x]
    mov edx, [x+4]      ; edx:eax = x
    
    push dword 0
    call [exit]
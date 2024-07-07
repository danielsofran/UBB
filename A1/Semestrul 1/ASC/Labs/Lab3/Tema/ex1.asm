bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    a db 01
    b dw 0AAAAh
    c dd 022222222h
    d dq 011111111FFFFFFFFh

;d-(a+b)+(c+c) - FARA SEMN
segment code use32 class=code
start:
    ; a+b
    mov al, [a]     ; al = a
    mov ah, 0       ; ax = a
    add ax, [b]     ; ax = a + b = AAAB
    
    mov bx, 0       
    adc bx, word 0  ; bx = CF - overflow ul
    
    push bx
    push ax
    pop eax         ; eax = a + b
    
    sub [d], eax    ; d = d - (a+b) = 1111 1111 FFFF 5554
    sbb [d+4], dword 0 ; in caz de overflow
    
    mov eax, [c]      ; eax = c = 2222 2222
    
    add [d], eax            ; 
    adc [d+4], dword 0      ; d = d - (a+b) + c
    
    add [d], eax            ; 
    adc [d+4], dword 0      ; d = d - (a+b) + (c+c) = 1111 1112 4443 9998
    
    mov eax, dword [d]
    mov edx, dword [d+4]    ; edx:eax = d
    
    push dword 0
    call [exit]
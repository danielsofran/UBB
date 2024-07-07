bits 32 
global start        
extern exit               
import exit msvcrt.dll    

segment data use32 class=data
    ; a db 10
    ; b db 20
    ; c db 10
   a db 30
   b db 20
   c db 10
   rez resw 1

segment code use32 class=code
    start:
        ; ;a+b/c
        ; mov al, [b] ; al = b
        ; mov ah, 0   ; ax = b
        ; div BYTE [c]; al = ax/c
        ; add al, [a] ; al += a
        
        ; a-b*c
        mov al, [b]
        mul BYTE [c] ;ax = al*c = 20
        
        ; bx = a
        mov bl, [a]
        mov bh, 0
        ; a-bx
        sub bx, ax
        
        mov [rez], bx
        
        push    dword 0     
        call    [exit]     

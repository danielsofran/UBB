bits 32 ; assembling for the 32 bits architecture
global start        
extern exit
import exit msvcrt.dll

segment data use32 class=data
    a DB 7
    b dw 0abcdh     ; 
    c dd 1234abcdh

segment code use32 class=code
    start:
        ; incarcarea unui registru cu o valoare imediata
        ;-------------------------------------------------
        mov eax, 0
        ; mov al, 7
        ; mov al, 7h
        ; mov al, 111b
        
        ; incarcarea valorii unei variabile
        mov eax, [a]
        
        ; incarcarea adresei(offset-ului) unei variabile
        mov eax, a
        
        ; INCORECT - AL are 8 biti, dar 256 are 9 biti
        ; mov al, 256
        mov eax, 256
        
        ; INCORECT - BL are 8 biti, dar b este word
        ; mov bl, [b]
        mov bx, [b]
        
        ; INCORECT 
        ; mov [a], [b]
        mov bx, [a]
        mov [b], bx
        
        ; suma
        mov al, 7
        add al, 5 
        
        ; a + 5
        mov al, [a] ; AL = a
        add al, 5   ; AL = a + 5
        
        ; a - 3
        mov bl, [a] ; BL = a
        sub bl, 3   ; BL = a-3
        
        ; a+b
        mov ax, [b] ; AX = b
        mov bh, 0   
        mov bl, [a] ; BX = 00000000 xxxxxxxx
        add ax, bx  ; AX = AX + BX = a + b
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program

bits 32
global start

extern exit
import exit msvcrt.dll

segment data use32 class=data
    s db 'abcdef'
    l equ $-s
    d resb l
    
; Se da un sir de caractere s.
; Se cere sirul de caractere d obtinut prin inversarea sirului s, folosind instructiuni pe siruri.
segment code use32 class=code
    start:
        mov ecx, l
        mov esi, s+l-1
        mov edi, d
        jecxz final
        
        bucla:
            std
            lodsb
            cld
            stosb
        loop bucla
        
        final:
        push dword 0        ; push the parameter for exit onto the stack
        call [exit]         ; call exit to terminate the program

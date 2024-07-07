bits 32
global start

extern printf
import printf msvcrt.dll

segment data use32 class=data
    s db 'abcdef'
    l equ $-s
    d resb l
    
; Se da un sir de caractere s.
; Se cere sirul de caractere d obtinut prin copierea sirului s, folosind instructiuni pe siruri.
segment code use32 class=code
    start:
        mov ecx, l
        mov esi, s
        mov edi, d
        cld
        ;jecxz pexit
        ;bucla:
        ;   movsb
        ;loop bucla
        rep movsb
        pexit:
        push d        ; push the parameter for exit onto the stack
        call [printf]         ; call exit to terminate the program

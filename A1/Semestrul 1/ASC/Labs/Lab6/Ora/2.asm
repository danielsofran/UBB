bits 32
global start

extern exit
import exit msvcrt.dll

segment data use32 class=data
    s db 'abcdef'
    l equ $-s
    d resb 6
    
; Se da un sir de caractere s.
; Se cere sirul de caractere d obtinut prin inversarea sirului s.
segment code use32 class=code
    start:
        mov esi, l
        dec esi
        mov edi, 0
        bucla:
            mov al, [s+esi]
            mov [d+edi], al
            inc edi
            dec esi
            cmp esi, 0
        jge bucla
        
        ; exit(0)
        push dword 0        ; push the parameter for exit onto the stack
        call [exit]         ; call exit to terminate the program

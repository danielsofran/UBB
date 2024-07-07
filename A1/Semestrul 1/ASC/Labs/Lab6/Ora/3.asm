bits 32
global start

extern exit
import exit msvcrt.dll

segment data use32 class=data
    s db 12h, 13h, 15h, 18h, 22h, 25h, 27h, 30h
    l equ $-s
    s1 resb 8
    s2 resb 8
    
; Se da un sir de numere intregi s.
; Se cere sa se determine sirurile de numere intregi:
; s1 - care contine doar numerele intregi pare din sirul s
; s2 - care contine doar numerele intregi impare din sirul s
segment code use32 class=code
    start:
        mov ecx, 0  ; index-ul din s
        mov eax, 0   ; index-ul lui s1
        mov ebx, 0   ; index-ul lui s2
        
        bucla:
            mov edx, [s+ecx]
            test edx, 1h
            jnz impar
                mov [s1 + eax], edx
                inc eax
                jmp final
            impar:
                mov [s2 + ebx], edx
                inc ebx
            final:
                inc ecx
                cmp ecx, l
        jb bucla
        ; exit(0)
        push dword 0        ; push the parameter for exit onto the stack
        call [exit]         ; call exit to terminate the program

bits 32
global start

extern exit
import exit msvcrt.dll

segment data use32 class=data
    s1 db 7, 3, 9, 1, 5
    s2 db 2, 4, 6, 8, 10
    l equ $-s2
    d resb l
    
; Se dau 2 siruri de numere intregi s1 si s2 de lungimi egale.
; Se cere sirul de numere intregi d obtinut astfel:
; - d[i] = s1[i] - s2[i], daca s1[i] > s2[i]
; - d[i] = s2[i] - s1[i], altfel.
segment code use32 class=code
    start:
        mov esi, 0 ; index s1, s2
        mov ecx, l
        
        jecxz final
        bucla:
            mov al, [s1 + esi]
            cmp al, [s2 + esi]
            ja mai_mare
            jbe mai_mic
            mai_mare:
                sub al, [s2 + esi]
                mov [d + esi], al
                jmp continue
            mai_mic:
                neg al
                add al, [s2 + esi]
                mov [d + esi], al
            continue:
                inc esi
        loop bucla
        final:
        ; exit(0)
        push dword 0        ; push the parameter for exit onto the stack
        call [exit]         ; call exit to terminate the program

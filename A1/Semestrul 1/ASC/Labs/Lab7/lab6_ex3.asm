bits 32
global start

extern printf
import printf msvcrt.dll

segment data use32 class=data
    s1 dw 1234h, 67abh, 89cdh
    l equ ($-s1)/2
    s2 dw 2345h, 5678h, 4567h
    d times l dw 0

; Se dau doua siruri de cuvinte s1 si s2.
; Se cere sirul de cuvinte d obtinut in interpretarea cu semn, astfel:
; - d[i] = s1[i], daca s1[i] > s2[i]
; - d[i] = s2[i], altfel.
segment code use32 class=code
    start:
        mov ecx, l
        mov ebx, 0 ; index pe d
        mov esi, s1
        mov edi, s2
        cld
        jecxz final
        
        repeta:
            lodsw
            stosw
            jle mai_mic
            
            lodsw
            mov [d+ebx], ax
            jmp continue
            
            mai_mic:
            mov [d+ebx], ax
            
            continue:
            add ebx, 2
        loop repeta
        
        final:
        push d        ; push the parameter for exit onto the stack
        call [printf]         ; call exit to terminate the program

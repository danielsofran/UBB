bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global pb23     

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    s db 1, 2, 2, 8, 2, 1, 3, 6, 2
    l equ $-s
    d resb 8

; our code starts here
segment code use32 class=code
    pb23:
        mov ecx, 0 ; index pe s
        mov edx, 0 ; lungimea&index pe d
        bucla_s: ; parcurgem s, s[ecx] = BYTE[s+ecx]
            ; il cautam pe s[ecx] in d
            mov ebx, 0 ; index pe d
            bucla_d: ; parcurgem d, d[ebx] = [d+ebx]
                mov al, byte [d+ebx] ; d[ebx]
                cmp al, [s+ecx]
                je sfarsit_bucla_s
                inc ebx
                cmp ebx, edx
            jb bucla_d
            append:
                mov al, byte[s+ecx]
                mov [d+edx], al
                inc edx
            sfarsit_bucla_s:
                inc ecx
                cmp ecx, l
        jb bucla_s
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the programs
bits 32
global start

extern exit
import exit msvcrt.dll
extern printf
import printf msvcrt.dll

segment data use32 class=data
    r dw 100
    msg db "Suta (de puncte): %d", 10, 0

segment code use32 class=code
start:
    push 100
    push msg
    
    call [printf]

    
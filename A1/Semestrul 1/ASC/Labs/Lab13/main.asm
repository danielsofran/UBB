bits 32

global start

;extern reverse

extern printf, scanf, exit, _getch
import printf msvcrt.dll
import scanf msvcrt.dll
import exit msvcrt.dll
import _getch msvcrt.dll

segment data use32 class=data
    format db "%s", 0
    cuvant resb 101
    cuvantrez resb 101
    format_output db "%s", 0
    mesaj_input db "Intruduceti o propozitie:", 10, 13, 0

segment code use32 class=code
start:
    push mesaj_input
    call [printf]
    add esp, 4
    
    cuvinte:
    
    push cuvant
    push format
    call [scanf]
    add esp, 4*2
    
    push cuvant
    call [printf]
    add esp, 4
    
    call [_getch]
    cmp eax, 10
    je final
    
    jmp cuvinte
    
    cmp eax, 1
    jne final
    
    
    
    jmp cuvinte
    
    push cuvantrez
    push cuvant
    ;call reverse
    add esp, 4*2
    
    push cuvantrez
    call [printf]
    add esp, 4
    
    final:
    push 0
    call [exit]
    
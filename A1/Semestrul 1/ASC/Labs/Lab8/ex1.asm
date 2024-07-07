bits 32
global start

extern exit, scanf
import exit msvcrt.dll
import scanf msvcrt.dll

segment data use32 class=data
    sir resb 100
    format db "%s", 0
    
    
; Cititi de la tastatura si afisati o propozitie
; (mai multe siruri de caractere separate prin SPATII si care se termina cu caracterul '.')
segment code use32 class=code
    start:
        
    push sir
    push format
    call [scanf]
    add esp, 4*2
    
    cmp eax, 0
    jne start
    
    push 
    
    ; exit(0)
    push dword 0        ; push the parameter for exit onto the stack
    call [exit]         ; call exit to terminate the program

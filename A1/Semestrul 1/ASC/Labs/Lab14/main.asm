bits 32

global start

extern exit, printf, scanf, fopen, fclose, fprintf
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll


segment data use32 class=data
    filename db "file.txt", 0
    mode db "w", 0
    descriptor dd 0
    format db "%X", 10, 0
    numar dw 257
    n16 dw 16
    
    
segment code use32 class=code
    start:
        push mode
        push filename
        call [fopen]
        add esp, 4*2
        
        cmp eax, 0
        je final
        
        mov [descriptor], eax
        
        
        ; 0-rizam partea sup din eax si edx
        cifre:
            mov eax, 0
            mov edx, 0
            mov ax, [numar]
            div word[n16] ; dx = restul la imp cu 16, adica cifra, ax = numarul ramas
            mov [numar], ax
            
            push edx
            push format
            push dword[descriptor]
            call [fprintf]
            add esp, 4*3
            
            cmp word[numar], 0
        jne cifre
        
        final:
        push    dword 0     
        call    [exit]      

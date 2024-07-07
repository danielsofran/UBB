bits 32

global start

extern exit, printf, scanf, fopen, fclose, fprintf, fread
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll
import fread msvcrt.dll


segment data use32 class=data
    filename db "test.txt", 0
    mode db "r", 0
    sir resb 101
    format db "%d", 0
    descriptor dd 0


segment code use32 class=code
    start:
        push mode
        push filename
        call [fopen]
        add esp, 4*2
        
        cmp eax, 0
        je final
        
        mov [descriptor], eax
        
        push eax
        push 100
        push 1
        push sir
        call [fread]
        add esp, 4*4
        
        
        mov esi, sir
        l:
            lodsb
            cmp al, 0
            je next
            jmp l
        next:
            mov eax, esi
            sub eax, sir+1
            
            push eax
            push format
            call [printf]
            add esp, 4*2
            
            push dword[descriptor]
            call [fclose]
            add esp, 4
        
        final:
        push    dword 0     
        call    [exit]      

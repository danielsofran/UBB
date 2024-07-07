bits 32

global start

extern exit, printf, scanf, fopen, fclose, fprintf, fscanf
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll
import fscanf msvcrt.dll


segment data use32 class=data
    filename db "file1.txt", 0
    descriptor dd 0
    mode db "w", 0
    format db "%c", 0
    formatp db "%d", 0
    index dd 0
    text db "ana aren mere", 0
    

segment code use32 class=code
    start:
        push mode
        push filename
        call [fopen]
        add esp, 4*2
        
        cmp eax, 0
        je final
        mov [descriptor], eax
        
        mov esi, text
        caract:
            lodsb
            cmp al, 0
            je final
            cmp al, 'a'
            jb scrie
            cmp al, 'z'
            ja scrie
            
            test dword[index], 1
            jnz scrie
            
            push dword[index]
            push formatp
            push dword[descriptor]
            call [fprintf]
            add esp, 4*3
            
            inc dword[index]
            jmp caract
            
            scrie:
                mov ebx, 0
                mov bl, al
                push ebx
                push format
                push dword[descriptor]
                call [fprintf]
                add esp, 4*3
                inc dword[index]
                jmp caract
        
        push dword[descriptor]
        call [fclose]
        add esp, 4
        
        final:
        push    dword 0     
        call    [exit]      

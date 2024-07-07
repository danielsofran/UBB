bits 32
global start

extern tob2

extern exit, printf, gets, system
import exit msvcrt.dll
import gets msvcrt.dll
import printf msvcrt.dll
import system msvcrt.dll


segment data use32 class=data
    format_b16 db "baza 16: %x ", 10, 0
    format_s db "baza 2: %s ", 10, 0
    format_b10 db "%d ", 0
    lastissep db 0
    sir resb 501
    snrb2 resb 33
    numar dd 0
    zece dd 10
    cmd db "pause", 0

segment code use32 class=code
start:
    ; citim sirul
    push sir
    call [gets]
    add esp, 4
    
    mov esi, sir
    cld
    catactere:
        mov eax, 0
        lodsb
        cmp al, 0
        je ultimul
        cmp al, '0'
        jl .separator
        cmp al, '9'
        jg .separator
        
        ; caracterul este cifra
        mov byte [lastissep], 0
        sub al, '0'
        push eax
        ; ogl = 10*ogl + cifra
        mov eax, [numar]
        mul dword[zece] ; edx:eax -> eax
        add eax, [esp]
        add esp, 4 ; aka pop
        mov [numar], eax
        jmp .continue
        
        .separator:
        shl byte [lastissep], 1 ; ajunge in cf
        jc .continue
        
        push dword [numar]
        push format_b16
        call [printf]
        add esp, 4*2
        
        ; determinam repr in baza 2
        push snrb2
        push dword [numar]
        call tob2
        add esp, 4*2
        
        push snrb2
        push format_s
        call [printf]
        add esp, 4*2
        
        mov dword [numar], 0
        mov byte [lastissep], 1
        .continue:
    jmp catactere
    
    ultimul:
        push dword [numar]
        push format_b16
        call [printf]
        add esp, 4*2
        
        ; determinam repr in baza 2
        push snrb2
        push dword [numar]
        call tob2
        add esp, 4*2
        
        push snrb2
        push format_s
        call [printf]
        add esp, 4*2
    
    push cmd
    call [system]
    add esp, 4
    
    push 0
    call [exit]

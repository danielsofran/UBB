bits 32 
global start        

extern exit, printf
import exit msvcrt.dll
import printf msvcrt.dll

segment data use32 class=data
    sir db "aba", 0
    rast_sir db "FFF", 0
    ispal db "Sirul este palindrom", 0
    nopal db "Sirul nu este palindrom", 0
    

; our code starts here
segment code use32 class=code
    len: ; lungimea unui sir de caractere -> parcurgem sirul pana la caracterul 04
         ; esp+4 - adresa sirului
        push esi ; esp + 12
        push ebx ; esp + 8
        pushfd ; esp + 4
        
        mov esi, [esp+16]
        mov ebx, 0
        cld
        .caractere:
            lodsb
            cmp al, 0
            je .gata
            inc ebx
            jmp .caractere
        
        .gata:
        mov eax, ebx ; eax = nr de caractere
        popfd
        pop ebx
        pop esi
        ret 4
    
    rast: ; sirul rasturnat
          ; esp+4 - sirul care urmeaza sa fie rasturnat
        mov esi, [esp+4]
        mov edi, [esp+8]
        
        push esi
        call len

        add edi, eax
        std
        mov al, 0
        stosb
        .caractere:
            cld
            lodsb
            cmp al, 0
            je .gata
            std
            stosb
            jmp .caractere
        .gata:
        cld
        ; mutam rezultatul in argument
        ret 4*2
    
    
    egale: ; sunt egale daca cf = 0 (scaderea lor fictiva e 0)
        mov esi, [esp+4]
        mov edi, [esp+8]
        
        push esi
        call len
        mov ecx, eax
        .caractere:
            cmpsb
            jne .set_0
        loop .caractere
        ; set 1
        stc
        ret 8
        .set_0:
            clc
            ret 4*2
        
    
    start:
        push rast_sir
        push sir
        call rast
        
        push sir
        push rast_sir
        
        push rast_sir
        push sir
        call egale
        
        jc print_pal
        
        push nopal
        call [printf]
        add esp, 4
        jmp exitp
        
        print_pal:
        push ispal
        call [printf]
        add esp, 4
        
        
        exitp:
        push dword 0
        call exit
        


bits 32

global _prefixmax
extern _printf

segment data use32 class=data public
    format_d db "%d ", 10, 0 
    format_c db "%c ", 10, 0
    lgmin dd 0
    
segment code use32 class=code public
    _prefixmax:
        push ebp
        mov ebp, esp ; cream cadrul de stiva
        sub esp, 4 ; alocam int pa stiva [ebp-4]
        pushad ; salvam valorile registriilor pe stiva
        

        ; CADRUL DE STIVA
        ; [ebp + 0] = valoarea ebp pentru apelant
        ; [ebp + 4] = adresa de retur
        ; [ebp + 8] = sir1
        ; [ebp + 12] = sir2
        ; [ebp + 16] = sir_rez
        cld
        mov esi, [ebp + 8]
        mov edi, [ebp + 12]
        mov ebx, [ebp+16]
        
        caractere:
            cmpsb
            jne next
            
            mov eax, 0
            mov al, [esi]
            
            push eax
            push format_c
            call _printf
            add esp, 4*2
            
            ; stosb in ebx
            dec esi
            lodsb
            cmp al, 0
            je next
            mov [ebx], al
            inc ebx 

        jmp caractere 
        next: ; stocam caracterul 0
        mov byte [ebx], 0
        
        push dword[ebp+16]
        call _printf
        add esp, 4

    final:
        popad ; restauram valorile registriilor
        add esp, 4
        mov esp, ebp
        pop ebp ; restauram stiva

        ret
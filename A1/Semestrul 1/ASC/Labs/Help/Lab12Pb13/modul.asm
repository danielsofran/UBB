bits 32

global _sufixmax
extern _strlen, _printf

segment data use32 class=data public
    format_d db "%d ", 10, 0 
    format_c db "%c ", 10, 0
    lgmin dd 0
    
segment code use32 class=code public
    _sufixmax:
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
        
        ; strlen(sir1);
        mov ebx, dword [ebp+8]
        
        push ebx
        call _strlen
        add esp, 4
        
        ; eax = lungimea sirului 1
        ; ana\0
        
        mov esi, [ebx+eax-1] ; finalul sirului 1
        mov [ebp-4], eax
        
        ; strlen(sir2);
        mov ebx, dword [ebp+12]
        
        push ebx
        call _strlen
        add esp, 4
        
        mov edi, [ebx+eax-1] ; finalul sirului 2
        
        cmp [ebp-4], eax
        jl parcurgere
        mov [ebp-4], eax
        
        ; comparam esi si edi de la sfarsit spre inceput
        parcurgere:
        mov ecx, [ebp-4]
        mov ebx, [ebp+16]
        caractere:
            std
            cmpsb
            jne next
            
            mov eax, 0
            mov al, [esi]
            
            push eax
            push format_c
            call _printf
            add esp, 4*2
            
            std
            ; stosb in ebx
            inc esi
            lodsb
            cmp al, 0
            je next
            mov [ebx], al
            inc ebx 
            
            dec ecx
            cmp ecx, 0
        je caractere 
        next: ; stocam caracterul 0
        mov byte [ebx], 0
        
        push ebx
        call _printf
        add esp, 4

    final:
        popad ; restauram valorile registriilor
        add esp, 4
        mov esp, ebp
        pop ebp ; restauram stiva

        ret
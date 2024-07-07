bits 32

global translate

segment code use32 class=code
translate: ; translate(sir, alfabet) -> substituie sirul dat cu cel translatat
    push ebp
    mov ebp, esp
    pushad
    
    mov ebx, [ebp+12]
    mov esi, [ebp+8]
    mov edi, [ebp+8]
    
    .caractere:
        lodsb
        cmp al, 0
        je .gata
        sub al, 'a'
        xlat ; AL = DS:EBX + AL
        stosb
    jmp .caractere
    
    .gata
    popad
    mov esp, ebp
    pop ebp
    ret

    
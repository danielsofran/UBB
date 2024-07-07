bits 32

global reverse

segment code use32 class=code
reverse:
    ; return adress -> [esp]
    ; vector -> [esp+4]
    ; lungime -> [esp+8]
    
    ; interschimbam elementele cu indicii esi si edi cat timp este posibil
    
    mov ebx, [esp+4]
    mov esi, 0
    mov edi, [esp+8]
    dec edi
    
    bucla:
        mov eax, [ebx+4*esi]
        mov edx, [ebx+4*edi]
        mov [ebx+4*esi], edx
        mov [ebx+4*edi], eax
        inc esi
        dec edi
        mov eax, edi
        sub eax, esi
        cmp eax, 0
    jg bucla
    ret

    
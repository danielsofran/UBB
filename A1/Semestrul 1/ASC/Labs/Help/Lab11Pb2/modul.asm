bits 32

global conversie

segment data use32 class=data
    zece dd 10
    rez dd 0

segment code use32 class=code
conversie:
    ; return adress - [esp]
    ; sir - [esp+4]
    mov byte [rez], 0
    mov esi, [esp+4]
    mov ebx, 0
    _loop:
        mov eax, 0
        lodsb
        cmp al, 0
        jz gata
        
        sub al, '0'
        mov ebx, eax ; ebx = c
        mov eax, [rez] ; eax = rez
        mul dword[zece]; eax *= 10
        add eax, ebx   ; eax += c
        mov [rez], eax ; rez = eax
    jmp _loop
    gata:
    mov eax, [rez]
    ret 4*1

    
bits 32

global tob2

segment code use32 class=code
tob2:
    ; return adress - [esp]
    ; numar - [esp+4]
    ; sir - [esp+8]
    mov ecx, 32
    mov edi, [esp+8]
    cld
    biti:
        shl dword [esp+4], 1 ; mutam primul bit in CF
        jc .bit1
        ; altfel bitul e 0
        ; stosb '0'
        mov al, '0'
        stosb
        jmp .continue
        .bit1:
        mov al, '1'
        stosb
        .continue:
    loop biti
    mov al, 0
    stosb
    ret

    
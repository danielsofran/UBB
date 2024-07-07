bits 32

global _show_signed_unsigned

extern _printf

segment data use32 class=data
    val dd 0
    mesaj_semn db "cu semn: %d", 10, 13, 0
    mesaj_fara db "fara semn: %u", 10, 13, 0
    frms db "%s", 0
    frm16 db "%X", 0

segment code use32 class=code
_show_signed_unsigned:
    push ebp
    mov ebp, esp
    
    mov esi, [ebp+8]
    mov edx, [ebp+12]

    mov ebx, 1
    mov ecx, edx
    sub ecx, 2
    rep sal ebx, 4
    mov ecx, 0
    
    ; push ebx
    ; push mesaj_semn
    ; call _printf
    ; add esp, 4*2
    
    mov edi, 0
    cld
    bucla:
        lodsb
        cmp al, 0
        je next
        
        cmp al, '9' ; '0'+9
        jle cifra
        ; else e litera
        sub al, 'A'-10
        jmp continue
        cifra:
        sub al, '0'
        continue:
        
        ; push eax
        ; push mesaj_semn
        ; call _printf
        ; add esp, 4*2
        MOV EBX, 0
        MOV BL, AL
        SAL EDI, 4
        ADD EDI, EBX
        
        inc ecx
        jmp bucla
    next:
    
    push edi
    push mesaj_semn
    call _printf
    add esp, 4*2
    
    push edi
    push mesaj_fara
    call _printf
    add esp, 4*2
    
    mov esp, ebp
    pop ebp
    ret 
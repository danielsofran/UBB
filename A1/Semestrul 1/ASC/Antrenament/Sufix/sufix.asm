bits 32
global start
extern printf, exit
import printf msvcrt.dll
import exit msvcrt.dll

segment data use32 class=data
    s1 db "enjenejiaria", 0
    s2 db "aria", 0
    
segment code use32 class=code
start:
    push s1
    push s2
    call sufix
    add esp, 4*2
    
    push eax
    call [printf]
    add esp, 4
    
    push 0
    call [exit]

sufix:
    ; [esp+4] - al doilea sir
    ; [esp+8] - primul sir
    ; eax - sirul rezultat
    mov esi, [esp+4]
    c1: 
        lodsb
        cmp al, 0
        je nc1
        jmp c1
    nc1:
    sub esi, 1
    mov edi, esi    ; edi - sf celui de al doilea sir
    mov esi, [esp+8]
    c2: 
        lodsb
        cmp al, 0
        je nc2
        jmp c2
    nc2:
    sub esi, 1         ; esi - sfarsitul primului sir
    std
    bucla:
        cmpsb
        jne incs
        cmp esi, [esp+8] ; comparam adresa sirului curent cu adresa sirului initial
        jb final
        cmp edi, [esp+4]
        jb final
        jmp bucla
    incs:
        inc esi
        inc edi
    final:
        cld
        add esi, 1
        mov eax, esi
        ret
        
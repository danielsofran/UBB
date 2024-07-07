bits 32

global maxbyte

extern formatd, formats, formatx
extern printf
import printf msvcrt.dll

%macro PRINTF 2
pushad
push %2
push %1
call [printf]
add esp, 4*2
popad
%endmacro

segment code use32 class=code
maxbyte:
    mov edx, 0
    mov esi, [esp+4]
    mov ecx, 4
    _loop:
        lodsb
        cmp al, dl
        jna continue
        mov dl, al
        mov edi, [esp+12]
        mov [edi], ecx
        ;PRINTF formatx, edx
        continue:
    loop _loop
    
    mov eax, [esp+8]
    add [eax], edx
    mov eax, edx
    ;PRINTF formatd, dword[eax]
    ret
    
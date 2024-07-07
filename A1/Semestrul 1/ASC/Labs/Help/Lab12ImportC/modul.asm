bits 32

extern _cod, _printf
global _expresie

segment data use32 class=data
    sir db "Hello", 0
    format db "%d", 0
    val dd 0

segment code use32 class=code
_expresie:
    push ebp
    mov ebp, esp
    
    mov edx, [ebp+8]
    
    
    
    push edx
    call _cod
    add esp, 4
    
    
    ;mov eax, 0
    
    push eax
    push format
    call _printf
    add esp, 4*2
    
    ; push sir
    ; call _printf
    ; add esp, 4*2
    
    mov esp, ebp
    pop ebp
    ret 
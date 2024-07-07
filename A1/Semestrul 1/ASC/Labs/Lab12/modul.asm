bits 32

global _expresie

segment data use32 class=data
    val dd 0

segment code use32 class=code
_expresie:
    push ebp
    mov ebp, esp
    
    mov ecx, [ebp+8]
    add ecx, [ebp+12]
    sub ecx, [ebp+16]
    mov [val], ecx
    
    mov esp, ebp
    pop ebp
    mov eax, [val]
    ret 
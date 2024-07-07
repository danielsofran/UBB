; Se dau cuvantul A si octetul B. Sa se obtina dublucuvatul C:
; bitii 0-3 ai lui C au valoarea 1
; bitii 4-7 ai lui C coincid cu bitii 0-3 ai lui A
; bitii 8-13 ai lui C au valoarea 0
; bitii 14-23 ai lui C coincid cu bitii 4-13 ai lui A
; bitii 24-29 ai lui C coincid cu bitii 2-7 ai lui B
; bitii 30-31 au valoarea 1

bits 32
global start
extern exit
import exit msvcrt.dll

segment data use32 class=data
    a dw 1110000000011010b 
    b db 00110011b
    c dd 0 ; 11 001100 1000000001 000000 1010 1111 = CC8040AF
    
segment code use32 class=code
start:
    or byte [c], 0Fh ; bitii 0-3 ai lui C au valoarea 1
    
    mov bl, [a]
    shl bl, 4      ; bl - bitii 0-3 din A + 0000b
    or byte[c], bl ; bitii 4-7 ai lui C coincid cu bitii 0-3 ai lui A
    
    mov ebx, 0
    mov bx, [a]
    and ebx, 0011111111110000b ; iau bitii 4-13 ai lui A
    shl ebx, 10                ; bitii ajung pe pozitia 14 in ebx
    or dword[c], ebx           ; bitii 14-23 ai lui C coincid cu bitii 4-13 ai lui A
    
    mov ebx, 0
    mov bl, [b]
    shr bl, 2   ; bitii 2-7 din B ajung pe poz 0 in masca
    shl ebx, 24 ; bitii 2-7 din B ajung pe poz 24 in masca
    or dword[c], ebx ; bitii 24-29 ai lui C coincid cu bitii 2-7 ai lui B
    
    mov ebx, 11b     ; 2 biti de 1
    shl ebx, 30      ; ajung pe poz 30-31 in masca
    or dword[c], ebx ; ajung in rezultat: bitii 30-31 au valoarea 1
    
    mov eax, [c]
    push dword 0
    call [exit]
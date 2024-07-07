bits 32

global start

extern exit, printf, scanf, fopen, fclose, fprintf, fscanf
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll
import fscanf msvcrt.dll


segment data use32 class=data
    sir dq 1110111b, 1<<32, 0ABCD0002E7FCh
    len equ ($-sir)/8
    rez resd len
    ct3 db 0
    nr dd 0
    format db "%c", 0
    pus1 db 0
    rang db 0
    caract dd '0'
    ctor dd 0

segment code use32 class=code
    start:
        mov esi, sir
        mov edi, rez
        mov ecx, len
        cld
        dcv:
            lodsd
            mov [nr], eax
            mov byte[ct3], 0
            mov byte[rang], 0
            .biti:
                shl eax, 1
                jnc reset
                ; bit 1
                inc byte[ct3]
                cmp byte[ct3], 3
                jl .biti
                ; else ct3 = 3
                inc byte[rang]
                mov byte[ct3], 0
                cmp byte[rang], 2
                jl reset
                ; stocam
                mov eax, [nr]
                inc dword[ctor]
                stosd
                jmp gata
                
                reset:
                mov byte[ct3], 0
                cmp eax, 0
                jne dcv.biti
            gata:
                lodsd
        loop dcv
        
        mov esi, rez
        mov ecx, [ctor]
        afisare:
            lodsd
            mov byte[pus1], 0
            push ecx
            mov ecx, 32
            .biti:
                rol eax, 1
                jnc b0
                ; bit1
                mov byte[pus1], 1
                adc dword[caract], 0
                jmp pt
                b0: ; bitul 0
                cmp byte[pus1], 0
                je continue1
                pt:
                pushad
                push dword[caract]
                push format
                call [printf]
                add esp, 4*2
                popad
                continue1:
                mov dword[caract], '0'
            loop afisare.biti
            
            pop ecx
            pushad
            push ' '
            push format
            call [printf]
            add esp, 4*2
            popad
        loop afisare
        push    dword 0     
        call    [exit]   
    ; start:
        ; push '0'
        ; push format
        ; call [printf]
        ; add esp, 4*2

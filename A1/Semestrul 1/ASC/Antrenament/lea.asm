bits 32

global start

extern exit, printf
import exit msvcrt.dll
import printf msvcrt.dll

segment data use32 class=data
    s1 dw 12abh, 34cdh, 56efh
    s2 dw 3500h, 0011h, 7ffbh
    len equ ($-s2)/2
    s resd len
    baiti resb 4
    nrbiti resb 4
    rez resd len
    format db "%d", 0
    mesaj db " ", 0
    bit dd 0

%Macro AFIS 1
    pushad
    push %1
    push format
    call [printf]
    add esp, 4*2
    push mesaj
    call [printf]
    add esp, 4
    popad
%endmacro

segment code use32 class=code
start:
    mov esi, s1
    mov edi, s
    mov ecx, len
    mov ebx, s2
    bucla:
        lodsw ; citim un cuvant din primul sir
        stosw ; il stocam in sirul de dublu cuvinte neordonat
        push esi ; salvam buffer ul din primul sir
        mov esi, ebx ; citim din al doilea sir
        lodsw ; citim cuvantul din al doilea sir
        stosw ; il stocam
        mov ebx, esi ; retinem buffer ul din al doilea sir
        pop esi ; restauram buffer ul din primul sir
    loop bucla
    
    mov ecx, len
    mov esi, s
    mov edi, rez
    bucla2:
        lodsd ; eax = un dublucuvant
        push esi ; salvam buffer ul
        mov [baiti], eax ; mutam eax intr-un vector de baiti
        
        ; initializam vectorul caracteristic nrbiti
        mov dword[nrbiti], 0 ; initializam cu 0
        mov esi, baiti ; vom parcurge acest vector
        ; nrbiti[i] = numarul de biti ai octetului i
        mov ebx, 0 ; index ul curent in sirul de octeti
        octeti:
            lodsb ; luam un octet
            mov edx, 8 ; vom parcurge bitii
            biti:
                rol al, 1 ; CF = un bit
                jnc continuebiti
                inc byte[nrbiti+ebx]
                continuebiti:
                dec edx
                cmp edx, 0
            jge biti
            inc ebx
            cmp ebx, 4 ; avem 4 octeti intr-un dublu cuvant
        jl octeti
        ; sortam crescator dupa nr de biti
        sortare:
            mov edx, 0 ; index la vectorii nrbiti si baiti
            bucla_sortare:
                mov al, [nrbiti+edx]   ; comparam nr de biti de 1
                mov ah, [nrbiti+1+edx]
                cmp al, ah
                jl continue_sortare
                je compar_valori
                ; altfel, daca NU sunt in ordine
                mov [nrbiti+1+edx], al ; interschimbam nr de biti
                mov [nrbiti+edx], ah
                mov al, [baiti+edx] ; interschimbam valorile octetilor
                mov ah, [baiti+1+edx]
                mov [baiti+edx], ah
                mov [baiti+1+edx], al
                jmp sortare ; vectorul nu e sortat, il mai sortam odata(met bulelor) 
                compar_valori:
                mov al, [baiti+edx]
                mov ah, [baiti+edx+1]
                cmp al, ah
                jle continue_sortare
                ; altfel, interschimbam
                mov [nrbiti+1+edx], al ; interschimbam nr de biti
                mov [nrbiti+edx], ah
                mov al, [baiti+edx] ; interschimbam valorile octetilor
                mov ah, [baiti+1+edx]
                mov [baiti+edx], ah
                mov [baiti+1+edx], al
                jmp sortare ; vectorul nu e sortat, il mai sortam odata(met bulelor) 
                continue_sortare:
                inc edx
                cmp edx, 3 ; parcurgem cei 3+1 baiti
            jl bucla_sortare
            
        ; avem vectorul baiti sortat
        ; il mutam in mem
        mov eax, [baiti]
        stosd
        ; avem in nrbiti numerul de biti din fiecare octet al dublucuvantului
        ; sortam sirul de octeti dupa nr de biti
        pop esi
        dec ecx
        cmp ecx, 0
    jge bucla2
    
    ;afisare
    mov ecx, len
    mov esi, rez
    bucla3:
        lodsd
        ; eax = dublu cuvant sortat
        push ecx
        mov ecx, 32
        bitiafis:
            mov dword[bit], 0
            AFIS dword[bit]
            rol eax, 1 ; CF = bitul din stanga
            adc dword[bit], 0 ; mut bit-ul in edx
            pushad ; nu distrugem registrii
            push dword[bit]
            push format
            call [printf] ; afisez val din edx
            add esp, 4*2
            popad
        loop bitiafis

        pushad ; nu distrugem registrii
        push mesaj
        call [printf] ; afisez separator
        add esp, 4
        popad
        
        pop ecx
    loop bucla3
    
    push    dword 0     
    call    [exit]      

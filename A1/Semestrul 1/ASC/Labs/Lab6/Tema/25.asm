bits 32
global start        
extern exit               
import exit msvcrt.dll    

; Se da un sir de octeti. Sa se obtina sirul oglindit al reprezentarii binare a acestui sir de octesi.
segment data use32 class=data
    s DB 0ABh, 0CDh, 0EFh   ; 10101011 11001101 11101111b 
    l equ $-s       
    d resb l                ; 11110111 10110011 11010101b = F7 B3 D5h

; our code starts here
segment code use32 class=code
    start:
        mov ecx, l
        mov esi, s+l-1
        mov edi, d
        
        parcurge: ; parcurgem sirul de octeti in ordine inversa
        push cx   ; salvam valoarea contorului de octeti
            std   ; incarcam in ordine inversa
            lodsb ; incarcam in al ultimul octet al sirului
            cld   ; stocam in ordine normala
            
            ; inversam ordinea bitilor
            mov bh, 0 ; octetul cu bitii inversati
            mov dh, 7 ; ordinul bitului high - de unde luam biti
            mov dl, 0 ; ordinul bitului low - unde punem biti
            
            biti:              ; parcurgem bitii octetului incarcat in al
                push ax        ; salvam octetul - suntem interesati doar de al
                    mov bl, 0  ; bitul pe care il punem in octetul cu bitii inversati
                    mov cl, dh ; punem pozitia de la care luam bitul in cl pentru a-l puea izola
                    shr al, cl ; obtinem bitul din reprezentarea inversa in al
                    or bl, al  ; mutam rezultatul in bl
                    and bl, 1  ; stergem bitii dinaintea bitului calculat
                    mov cl, dl ; transferam pozitia la care punem bitii in cl, pentru a putea efectua poeratia
                    shl bl, cl ; punem bitul din reprezentare inversa la pozitia sa din reprezentarea normala
                    or bh, bl  ; punem bitul in rezultat = BH
                pop ax ; restauram valoarea octetului
                
                inc dl ; crestem pozitia la care punem bitii
                dec dh ; descrestem pozitia de la care luam bitii 
                cmp dh, 0 ; pana ajunge 0 inclusiv
            jge biti
            
            mov al, bh ; punem rezultatul in al pentru a putea fi stocat
            stosb
        pop cx ; restauram valoarea contorului de octeti
        loop parcurge
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program

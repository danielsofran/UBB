bits 32 ;asamblare și compilare pentru arhitectura de 32 biți
; definim punctul de intrare in programul principal
global start

; declaram functiile externe necesare programului nostru 
extern exit ; indicam asamblorului ca exit exista, chiar daca noi nu o vom defini
import exit msvcrt.dll  ; exit este o functie care incheie procesul, este definita in msvcrt.dll
        ; msvcrt.dll contine exit, printf si toate celelalte functii C-runtime importante

; segmentul de date in care se vor defini variabilele 
segment data use32 class=data
    sir DB 2, 4, 2, 5, 2, 2, 4, 4 ; declaram sirul sir
    l equ $-sir ; l retine lungimea sirului si o converteste pentru word 
    lsubsir dd 0

segment code use32 class=code
start:
    mov ecx, l ; retinem in ecx lungimea sirului
    
    ;jecxz Sfarsit ; daca ecx=0 atunci nu vom avea de efectuat calcule (sirul este gol)
    mov esi,sir ; punem in esi offset-ul sirului sursa (,,sir")
    ;mov edi,D ; punem in edi offset-ul sirului destinatie (D)
    cld ; vom parcurge sirul ,,sir" de ls stanga la drerapta (DF=0)
    mov edi, 0
    Sortare:
        lodsb
        mov bl, al ; BL = esi[l-ecx] elementul curent = elementul minim
        
        ; iau indexul curent
        mov edx, edi
        
        dec ecx
        mov [lsubsir], ecx
        push edi
        push esi
        jecxz Sfarsit_sortare
        Parcurgere: ; determina minimul secventei ramase
            LODSB 
            cmp al, bl
            JGE Sfarsit_parcurgere
            
            mov bl, al ; iau indexul curent
            mov edx, edi
            inc edx
            
            Sfarsit_parcurgere:
            inc edi
        loop Parcurgere
        mov ecx, [lsubsir]
        inc ecx
        pop esi
        pop edi
        
        ; interschimbam valoararea curenta cu cea minima
        mov al, byte[sir+edi]
        mov byte[sir+edi], bl
        mov byte[sir+edx], al
        Sfarsit_sortare:
            inc edi
            dec ecx
            cmp edi, l
    jl Sortare
    
    Sfarsit:
    
    ; exit(0)
    push dword 0 ; se pune pe stiva parametrul functiei exit
    call [exit] ; apelul functiei exit pentru terminarea executiei programului
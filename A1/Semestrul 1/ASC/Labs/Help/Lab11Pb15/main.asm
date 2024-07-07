bits 32 ; assembling for the 32 bits architecture


global start        


extern exit,scanf,printf,gets,system
import system msvcrt.dll   
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll
import gets msvcrt.dll

extern concatenare


segment data use32 class=data public
    lungime_cuv dd 0
    len dd 0
    nr dd 0  
    max equ 500    ;lungimea propozitiei
    n dd 0                ;lungimea maxima a propozitiei
    format_citire_max db '%d',0
    mesaj_citire_max db "n=",0
    stop dd '.'                 ;caracterul de oprire
    space dd ' '                ;caracterul spatiu pentru a sti cand am citit un cuvant
    letter dd 0                 ;litera curenta
    ;nr_litere_cuv dd 0              ;nr litere a unui cuvant
    
    
    propozitie times max+1 db 0   ;buffer pentru propozitie

    mesaj_citire db "Introduceti n propozitii: ",10,0
    format_citire db '%c',0     ;formatul pentru citire c -deoarece citim caracter cu caracter
    
    format_afisare db '%d ',0    ;formatul pentru afisare d - deoarece afisam numarul de litere al fiecarui cuvant
    
    format_afis_prop db '%s',0  ;formatul pentru afisarea propozitiei
    format_citire_prop db '%[^.]%',0
    rez resb max+1
    cmd db 'pause',0
    bn db 10, 13, 0
    
    


 segment code use32 class=code public
    start:
        cld
        push dword mesaj_citire_max
        call [printf]
        add esp,4*1
        
        push dword n
        push dword format_citire_max
        call [scanf]
        add esp,4*2
        
        push dword mesaj_citire
        call [printf]
        add esp,4*1
      
        ;enter dupa numar 
        push dword propozitie
        call [gets]
        add esp,4*1
        
        mov ecx,dword[n]
        mov ebx,0
        
        citire_n_prop:
            push ecx
            
            push dword propozitie
            call [gets]
            add esp,4*1
            
            ; push dword propozitie
            ; call [printf]
            ; add esp,4*1
            
            push dword propozitie
            push dword rez
            push dword ebx
            call concatenare 
            add esp,4*3
            
            ; push dword rez
            ; call [printf]
            ; add esp,4*1
            ; push dword bn
            ; call [printf]
            ; add esp,4*1
            
            inc ebx
            pop ecx
        loop citire_n_prop
        
        push dword rez
        call [printf]
        add esp,4*1
        push cmd
        call [system]
        add esp,4
        push    dword 0      
        call    [exit]       ; call exit to terminate the program


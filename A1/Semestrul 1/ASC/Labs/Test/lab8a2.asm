; Codul de mai jos va afisa mesajul ”n=”, apoi va citi de la tastatura valoarea numarului n.
bits 32

global start        

; declararea functiilor externe folosite de program
extern exit, printf, scanf ; adaugam printf si scanf ca functii externa            
import exit msvcrt.dll    
import printf msvcrt.dll    ; indicam asamblorului ca functia printf se gaseste in libraria msvcrt.dll
import scanf msvcrt.dll     ; similar pentru scanf
                          
segment data use32 class=data
	a dd 0       ; in aceasta variabila se va stoca valoarea citita de la tastatura
    b dd 0
    rezultat dd 0
    msj_rez dd "a/b= %d"
    ; sirurile de caractere sunt de tip byte
	message  db "n=", 0  ; sirurile de caractere pentru functiile C trebuie sa se termine cu valoarea 0 (nu caracterul)
	format  db "%d", 0  ; %d <=> un numar decimal (baza 10)
    
segment code use32 class=code
    start:
       
        push dword a ; ! pe stiva se pune adresa string-ului, nu valoarea
        push dword format
        call [scanf]     
        add esp, 4 * 2      
        
        
        push dword b     
        push dword format
        call [scanf]      
        add esp, 4 * 2    
                           
        
        mov eax, [a]
        cdq
        idiv dword[b]
        mov [rezultat], eax
        
        push dword [rezultat]
        push dword msj_rez
        call [printf]
        add esp, 4 * 2
        
        
        push dword 0      ; punem pe stiva parametrul pentru exit
        call [exit]       ; apelam exit pentru a incheia programul
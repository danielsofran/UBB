bits 32
global start         

extern exit, printf, scanf, fopen, fclose, fprintf, fread, rename, remove
import exit msvcrt.dll
import printf msvcrt.dll 
import scanf msvcrt.dll 
import fopen msvcrt.dll 
import fclose msvcrt.dll 
import fprintf msvcrt.dll 
import fread msvcrt.dll 
import rename msvcrt.dll 
import remove msvcrt.dll 

segment data use32 class=data
    n32 dd 0
    n dw 0
    octet db 0
    
    msg_in db "Introduceti numarul in baza 16: ", 0
    format_b16 db "%X", 0
    format_c db "%c", 0
    descriptor dd 0
    mod_citire db "r", 0
    path db "in.txt", 0
    

segment code use32 class=code
start:
    ; afisare mesaj citire
    push msg_in
    call [printf]
    add esp, 4
    
    ; citire n
    push n32
    push format_b16
    call [scanf]
    add esp, 4*2
    mov ebx, [n32]
    mov [n], bx
    
    ; deschidere fisier
    push mod_citire
    push path
    call [fopen]
    add esp, 4*2
    
    ; verificare deschidere cu succes
    cmp eax, 0
    je inchide
    mov [descriptor], eax
    
    ; parcurgem bitii lui n de la dreapta la stanga si citim simultan cate un octeti
    mov ecx, 16
    bucla:
        push ecx
        ; citim octetul/caracterul
        push dword[descriptor]
        push 1 ; count
        push 1 ; size
        push octet ; rezultat
        call [fread]
        add esp, 4*4
        
        ; verificam daca bitul ne indica sau nu sa afisam octetul
        mov cl, 1
        ror word[n], cl
        jnc continue ; daca bitul nu e 1, nu il afisam
        
        ; altfel, afisam octetul
        mov ebx, 0
        mov bl, [octet]
        
        push ebx
        push format_c
        call [printf]
        add esp, 4*2
        
        continue:
        pop ecx
    loop bucla
    
    ; inchidem fisierul
    push dword[descriptor]
    call [fclose]
    add esp, 4
    
    inchide:
    push 0
    call [exit]
    
    
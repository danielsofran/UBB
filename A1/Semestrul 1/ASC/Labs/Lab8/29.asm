bits 32
global start
extern printf, fread, fscanf, fopen, fclose, exit
import fscanf msvcrt.dll
import printf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import exit msvcrt.dll
import fread msvcrt.dll

segment data use32 class=data
    fisier db "numere.txt", 0
    mesaj_afisare db "Maximul numerelor din fisierul %s este %d.", 0
    read db "r", 0
    descriptor dd 0
    format_input db "%d", 0
    numar dd 0
    rezultat dd -1

; numarul maxim dintr-un fisier
segment code use32 class=code
start:
    ; deschidem fisierul
    push read
    push fisier
    call [fopen]
    add esp, 4*2
    
    mov [descriptor], eax
    cmp eax, 0
    je final
    
    mov ecx, 0
    numere:
        ; citim din fisier
        push numar
        push format_input
        push dword[descriptor]
        call [fscanf]
        add esp, 4*3
        
        cmp eax, 1  ; verificam daca mai exista elemente
        jne next
        
        mov ebx, [numar]
        cmp ebx, [rezultat]
        jle numere
        
        mov [rezultat], ebx
        jmp numere
    
    next:
    ; afisam rezulatul
    push dword[rezultat]
    push fisier
    push mesaj_afisare
    call [printf]
    add esp, 4*3
    
    ; inchidem fisierul
    push dword[descriptor]
    call [fclose]
    add esp, 4
    
    final:
    push dword 0
    call [exit]
    
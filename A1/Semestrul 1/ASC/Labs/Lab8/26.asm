bits 32
global start

extern exit, fopen, fprintf, fclose, printf, scanf
import exit msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll
import fprintf msvcrt.dll

segment data use32 class=data
    filename db "fisier.txt", 0
    descriptor dd 0
    write db "w", 0
    sir resb 256 ; cat ii cuvantul?
    format_sir db "%s", 0
    format_afisare_sir db "%s", 10, 0
    mesaj_init db "Introduceti cuvinte, terminand cu '$':", 10, 0

; citim cv pana la introducerea '$' si afisam cv care contin o litera mare
segment code use32 class=code
start:
    ; cream fisierul
    push write
    push filename
    call [fopen]
    mov [descriptor], eax
    add esp, 4*2
    
    ; citim cuvinte de la tastatura
    push mesaj_init
    call [printf]
    add esp, 4
    
    ciclu:
        ; citim sirul
        push sir
        push format_sir
        call [scanf]
        add esp, 4*2
        
        procesare: ; procesam sirul
            ; cautam caractere uppercase
            mov esi, sir
            caractere:
                lodsb ; al = caracter
                cmp al, 0 ; verificam daca sirul s-a terminat
                je continue
                cmp al, 'A' ; verificam daca este mai mare sau egal cu 'A'
                jl caractere
                cmp al, 'Z' ; verificam daca este mai mic sau egal cu 'Z'
                jg caractere
                jmp scriere
            
        scriere: ; scriem in fisier cuvantul citit
            push sir
            push format_afisare_sir
            push dword[descriptor]
            call [fprintf]
            add esp, 4*3
        
        continue:
            ; verificam daca sirul introdus este "$" (nu numai sa-l contina)
            cmp byte[sir+1], 0 ; verifica daca are un singur caracter
            jne ciclu
            cmp byte[sir], '$' ; verifica daca primul caracter este '$'
            jne ciclu
    
    ; inchidem fisierul
    push dword[descriptor]
    call [fclose]
    add esp, 4
    
    push dword 0
    call [exit]
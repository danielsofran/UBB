bits 32
                
global concatenare

extern printf,system
import printf msvcrt.dll
import system msvcrt.dll

segment data use32 class=data public
    cmd1 db 'pause', 0
segment code use32 class=code public
concatenare:
    ;esp-return adress
    ;esp+4-i
    ;esp+8-adresa dest
    ;esp+12-adresa sursa 
    mov esi,[esp+8]
    cld
    
    citire:
        lodsb
        cmp al,0
        je adaugare
        jmp citire
    adaugare:
        ; push dword esi
        ; call [printf]
        ; add esp,4
        ; push cmd1
        ; call [system]
        ; add esp,4
        dec esi   
        mov edi,esi
        mov esi,[esp+12]
   
    mov ecx,[esp+4]
    jecxz cuv_gasit
      
    cuvant_n:
        lodsb
        cmp al,'A'
        jl .continue
        cmp al,'Z'
        jg .cmp2
        jmp cuvant_n
        .cmp2:
        cmp al,'a'
        jl .continue
        cmp al,'z'
        jg .continue
        jmp cuvant_n
        .continue:
        ; lodsb
        ; cmp al,'a'
        ; jl .continue
        ; cmp al,'z'
        ; jg .continue
        ; jmp cuvant_n
        ; .continue:
    loop cuvant_n
    
    cuv_gasit: ;esi pointeaza catre prima litera din cuvant
        lodsb
        cmp al, 'A'
        jl sfarsit_cuv
        cmp al, 'Z'
        jg .cmp2
        stosb ; else
        jmp cuv_gasit
        .cmp2:
        cmp al, 'a'
        jl sfarsit_cuv
        cmp al, 'z'
        jg sfarsit_cuv
        stosb
        ; lodsb
        ; cmp al,'a'
        ; jl sfarsit_cuv
        ; cmp al,'z'     
        ; jg sfarsit_cuv
        ; stosb
    jmp cuv_gasit
    
    sfarsit_cuv:
        mov al,' '
        stosb
        mov al,0
        stosb
         
   ret 
   
 
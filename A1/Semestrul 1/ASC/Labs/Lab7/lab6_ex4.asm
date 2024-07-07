bits 32
global start

extern exit
import exit msvcrt.dll

segment data use32 class=data
    s dd 11234456h, 22345567h, 33456678h
    l equ ($-s)/4
    d resw l

; Se da un sir de dublucuvinte s.
; Se cere sirul d ale carui elemente sunt cuvinte obtinute in interpretarea cu semn, astfel:
; - octetul inferior din d[i] este suma octetilor inferiori ai cuvintelor din fiecare dublucuvant al sirului s
; - octetul superior din d[i] este suma octetilor superiori ai cuvintelor din fiecare dublucuvant al sirului s
segment code use32 class=code
    start:
        mov ecx, l
        mov esi, s
        mov edi, d
        cld
        jecxz last
        
        e:
            ;!!!!!! atentie la ordine!!!!
            lodsw ; ax = partea low din s
            mov dl, al
            mov dh, ah
            lodsw ; ax = partea high din s
            add al, dl
            add ah, dh
            
            stosw
        loop e
        
        last:
        push dword 0        ; push the parameter for exit onto the stack
        call [exit]         ; call exit to terminate the program

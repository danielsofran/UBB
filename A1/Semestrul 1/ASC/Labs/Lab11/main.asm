bits 32
global start

extern translate

extern exit, scanf, printf, Sleep
import exit msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll
import Sleep kernel32.dll

segment data use32 class=data
    text resb 101
    alfabet db "OPQRSTUVWXYZABCDEFGHIJKLMN", 0
    mesaj_input db "Introduceti un sir: ", 0
    mesaj_output db "Traducerea dupa alfabetul %s este %s", 0
    format_input db "%s", 0
    millis dd 5000

segment code use32 class=code
start:
    push mesaj_input
    call [printf]
    add esp, 4
    
    push text
    push format_input
    call [scanf]
    add esp, 4*2
            
    ; translate(sir, alfabet) -> substituie sirul dat cu cel translatat
    push alfabet
    push text
    call translate
    add esp, 4*2
    
    push text
    push alfabet
    push mesaj_output
    call [printf]
    add esp, 4*3
    
    push 5000
    call [Sleep]
    add esp, 4
    
    push 0
    call [exit]

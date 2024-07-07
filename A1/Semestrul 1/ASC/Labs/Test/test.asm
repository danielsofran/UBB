bits 32; assembling for the 32 bits architecture 
global start
         
extern exit, printf, scanf; tell nasm that exit exists even if we won't be defining it 
import exit msvcrt.dll; exit is a function that ends the calling process. It is defined in msvcrt.dll 
import printf msvcrt.dll 
import scanf msvcrt.dll 


segment data use32 class=data 
    Message db "Data de azi este: ", 0 
    Format db "%d", 0 
    Read_message times 31 db 0 
    rezultat resb 100
    doublew dd 0
    numar dd 10

; our code starts here 
segment code use32 class=code 
start:
    mov ax, fs
    mov ebx, 0
    jmp far [ax:ebx]
    
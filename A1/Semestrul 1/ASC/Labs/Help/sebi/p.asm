bits 32 

extern _printf

global _transformare        
            
segment data use32 class=data
    cifreHexa db "0123456789ABCDEF"
    format db "%u", 10, 13, 0
    
; void convert(char* s,char* rezultat)
; {
    ; for (int i = 0; i<8; i++)
    ; {
        ; //1100 0010
        ; int cifHex = 0;
        ; cifHex *= 2;
        ; cifHex += s[i*4]-'0';
        ; cifHex *= 2;
        ; cifHex += s[i*4+1]-'0';
        ; cifHex *= 2;
        ; cifHex += s[i*4+2]-'0';
        ; cifHex *= 2;
        ; cifHex += s[i*4+3]-'0';
        ; rezultat[i] = cifreHexa[cifHex];
    ; }
    ; rezultat[8] = 0;
; }

segment code use32 class=code
_transformare:    
    ; [ebp + 8] = rezultat , [ebp + 12] = s
    push ebp
    mov ebp, esp
    sub esp, 4          ; rezervam spatiu pt adresa sirului de caractere
    
    pushad            
    
    mov esi, [ebp + 12]  ; mutam in EAX adresa
    mov edi, [ebp + 8]         
    mov ecx, 8
    cld
repeta:
    mov bl, 0 ; construim cifHex
    
    pushad
    mov ecx, 0
    mov cl, bl
    push ecx
    push format
    call _printf
    add ESP, 2*4
    popad
    
    ; shl bl, 1 ; inmultim cu 2
    ; lodsb ; AL = s[i], i++
    ; sub al, '0'
    ; add bl, al
    ; shl bl, 1 ; inmultim cu 2
    ; lodsb ; AL = s[i], i++
    ; sub al, '0'
    ; add bl, al
    ; shl bl, 1 ; inmultim cu 2
    ; lodsb ; AL = s[i], i++
    ; sub al, '0'
    ; add bl, al
    ; shl bl, 1 ; inmultim cu 2
    ; lodsb ; AL = s[i], i++
    ; sub al, '0'
    ; add bl, al
    ; mov al, bl
    ; mov ebx, cifreHexa
    
    pushad
    mov ecx, 0
    mov cl, bl
    push ecx
    push format
    call _printf
    add ESP, 2*4
    popad
    
    
    mov ebx, cifreHexa
    mov al, 0
       
    xlat   ; AL = cifreHexa[AL]
    stosb  ; rezultat[i++] = al    
    loop repeta
    mov al, 0 
    stosb
        
    
    popad   
    
    mov esp, ebp
    pop ebp
    
    ret
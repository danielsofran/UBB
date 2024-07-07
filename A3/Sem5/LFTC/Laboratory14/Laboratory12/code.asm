bits 32 
global start 

extern exit 
extern exit, printf, scanf 
import exit msvcrt.dll 
import printf msvcrt.dll 
import scanf msvcrt.dll 

segment data use32 class=data 
	read_format db "%d", 0 
	write_format db "%d", 0 
    swap_variable dd 0 
	aux dd 0 

	x dd 0 
	y dd 0 
	temp1 dd 0 
	temp2 dd 0 
	temp3 dd 0 
	temp4 dd 0 
	temp5 dd 0 
	z dd 0 
	temp6 dd 0 

segment code use32 class=code 
	start: 

push dword x 
push dword read_format 
call [scanf] 
add esp, 4*2 

push dword y 
push dword read_format 
call [scanf] 
add esp, 4*2 

mov eax, [x] 
sub eax, dword 6 
mov [temp1], eax 

mov eax, [temp1] 
add eax, dword 8 
mov [temp2], eax 

mov eax, [temp2] 
add eax, dword 6 
mov [temp3], eax 

mov eax, [temp3] 
add eax, dword 2 
mov [temp4], eax 

mov eax, [temp4] 
sub eax, dword 9 
mov [temp5], eax 

mov [swap_variable], edx 
mov edx, [temp5] 
mov [z], edx 
mov edx, [swap_variable] 

mov eax, [x] 
add eax, [y] 
mov [temp6], eax 

mov [swap_variable], edx 
mov edx, [temp6] 
mov [z], edx 
mov edx, [swap_variable] 

push dword [z] 
push dword write_format 
call [printf] 
add esp, 4*2 

push dword 0 
call [exit] 

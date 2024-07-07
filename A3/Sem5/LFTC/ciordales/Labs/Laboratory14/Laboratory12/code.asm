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
	y dd 2 
	temp1 dd 0 
	z dd 0 
	temp2 dd 0 
	temp3 dd 0 
	temp4 dd 0 
	temp5 dd 0 
	a dd 22 
	b dd 2 
	temp6 dd 0 

segment code use32 class=code 
	start: 

mov [swap_variable], edx 
mov edx, dword 2 
mov [y], edx 
mov edx, [swap_variable] 

mov eax, [y] 
add eax, dword 3 
mov [temp1], eax 

mov [swap_variable], edx 
mov edx, [temp1] 
mov [z], edx 
mov edx, [swap_variable] 

mov eax, dword 1 
add eax, [y] 
mov [temp2], eax 

mov eax, [temp2] 
add eax, [z] 
mov [temp3], eax 

mov [swap_variable], edx 
mov edx, [temp3] 
mov [x], edx 
mov edx, [swap_variable] 

mov [aux], dword 3 
mov eax, [x] 
mul dword [aux] 
mov [temp4], eax 

mov eax, [temp4] 
sub eax, [z] 
mov [temp5], eax 

mov [swap_variable], edx 
mov edx, [temp5] 
mov [y], edx 
mov edx, [swap_variable] 

mov [swap_variable], edx 
mov edx, dword 22 
mov [a], edx 
mov edx, [swap_variable] 

mov [swap_variable], edx 
mov edx, dword 2 
mov [b], edx 
mov edx, [swap_variable] 

mov edx, dword 0 
mov eax, [a] 
div dword [b] 
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

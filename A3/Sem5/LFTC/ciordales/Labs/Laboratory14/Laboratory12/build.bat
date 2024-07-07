nasm -fobj code.asm
alink code.obj -oPE -subsys console -entry start
code
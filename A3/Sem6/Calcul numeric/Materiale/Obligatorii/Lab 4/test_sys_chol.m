B=magic(5);
A=B'*B
b=(1:5)';
x=sol_sys_chol(A,b)
A*x

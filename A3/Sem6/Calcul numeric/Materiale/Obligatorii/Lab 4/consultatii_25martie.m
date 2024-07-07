pkg load statistics
#pb4
B=rand(10,10);
det(B);
A=B'*B; #simetrica si pozitiv definita
#R=Cholesky(A)
#R'*R==A #apar erori de rotunjire, nu e o idee buna sa comapri cu ==
#R'*R-A #se poate calcula norma si sa vedem ca e destul de mica, avem ordinul 16 => 0.000...
#norm(ans) #foarte aproape de 0

#pb4----------------------
#b=rand(10,1)
b=A*ones(10,1);
x=sol_sys_chol(A,b);
#norm(A*x-b) #daca da ceva mic e ok




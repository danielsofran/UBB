%Problema 1 - Cuadratura Gauss-Radau
syms x;
w=sqrt(1-x) % ponderea initiala
a=-sym(1), b=sym(1)
wa=(x-a)*w
pi2=orto_poly_sym_type('Jacobi',2,sym(1)/2,sym(1))
radacini=solve(pi2==0)'
nodes=[a,radacini]
coefs=gauss_coefs_sym(w,a,b,nodes)

rest_fara_f=int(pi2^2*wa,a,b)/factorial(sym(5))

f=sin(x)
int_gauss_radau=eval(sum(coefs.*subs(f,x,nodes)))

rest_worst=abs(eval(rest_fara_f))

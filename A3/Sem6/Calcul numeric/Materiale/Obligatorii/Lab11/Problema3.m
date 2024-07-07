%Problema 2 - Cuadratura Gauss-Radau
syms x;
w=exp(-x) % ponderea initiala
a=sym(0), b=sym(Inf)
wa=(x-a)*w
pi2=orto_poly_sym_type('Laguerre',2,sym(1))
radacini=solve(pi2==0)'
nodes=[a,radacini]
coefs=gauss_coefs_sym(w,a,b,nodes)

rest_fara_f=int(pi2^2*wa,a,b)/factorial(sym(5))

f=log(1+exp(-x))*exp(x)
int_gauss_radau=eval(sum(coefs.*subs(f,x,nodes)))

df5=diff(f,x,5)

%df5 depinde doar de e^x, unde x este intre 0 si Inf
% facem schimbarea de variabila e^x=1/y => y este intre 0 si 1
syms y;
fplot(function_handle(subs(df5,exp(x),1/y)),[0,1])
% se observa pe grafic ca functia este intre -0.1 si 0.1

rest_worst=abs(eval(rest_fara_f))*0.1

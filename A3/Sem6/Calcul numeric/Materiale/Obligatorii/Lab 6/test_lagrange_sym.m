x=[-1 0 1];
f=[1 1 3];
lagrange_sym(x,f);

lagrange_sym([-1 0 1],[1 1 3]);

%---------------------------------------------------

%pb3
nodes=[81 100 121 144]
values=[9 10 11 12]
L=lagrange_sym(nodes, values)
aprox=double(subs(L,115))
exact=sqrt(115)
err1=abs(aprox-exact)
syms csi
df4=diff(sqrt(csi),4)
%R=prod(115-nodes)/factorial(4)
%subs(abs(diff(sqrt(csi),4)),81)
err2=double(abs(prod(115-nodes)/factorial(sym(4))*subs(df4,81)))
%err2<10^-3 "True"
%sym(4) calculeaza perfect de fiecare data

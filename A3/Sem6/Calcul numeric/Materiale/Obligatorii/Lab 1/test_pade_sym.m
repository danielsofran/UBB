syms x
f=exp(x)
R22=pade_sym(f,2,2,x)
R22=pade_sym(f,2,2,x)
taylor(R22, 'order', 2+2+1)
taylor(f, 'order', 2+2+1)
double(subs(R22, sym(1)/2))

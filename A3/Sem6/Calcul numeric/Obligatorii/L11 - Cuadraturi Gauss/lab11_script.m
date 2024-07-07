a=-1, b=1
syms x;
w=1/sqrt(1-x^2)
f=sin(exp(x))
I=int(f*w,x,-1,1)

n=4
# pi4 = orto_poly_sym(w,x,a,b,n)
# pi4 = x^4 - x^2 +sym(1)/8
pi4 = orto_coef_sym_type('Cebisev1', n)
nodes = solve(pi4==0)'
coefs=gauss_coefs_sym(w,a,b,nodes)

IG_sym = sum(coefs.*subs(f,x,nodes))
# IG_nums = eval(IG_sym)
# I_octave = integral(function_handle(f*w), -1, 1)

I

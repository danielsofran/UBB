a=-1, b=1 #intervalul sa fie simbolic
syms x;
w=1/sqrt(1-x^2) #Cebisex#1 din tabel din notite
f=sin(exp(x))
I=int(f*w,x,-1,1)


n=4
#pi4=orto_poly_sym(w, x,a,b,n)

pi4=x^4-x^2+sym(1)/8 #Rn(p)

nodes=solve(pi4==0)'
coefs=gauss_coefs_sym(w,a,b,nodes)

IG_sym=sum(coefs.*subs(f,x,nodes)) #integrala gauss simbolica, prima formula din lab11_notite

format long
IG_num=eval(IG_sym) #integrala gauss numeric

I_octave=integral(function_handle(f*w),-1,1)



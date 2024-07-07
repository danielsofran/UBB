a=-1, b=1 #intervalul sa fie simbolic
syms x;
w=1/sqrt(1-x^2) #Cebisex#1 din tabel din notite
#f=sin(exp(x))
f=exp(x) #pentru rest ca sa se poata utiliza, trebuie o fucntie care sa se anuleze in capete
##I=int(f*w,x,-1,1)   #comentat ca sa mearga rest mai rapid


n=4
pi4=orto_poly_sym_type('Cebisev1',n)

#pi4=x^4-x^2+sym(1)/8 #Rn(p)

nodes=solve(pi4==0)'
coefs=gauss_coefs_sym(w,a,b,nodes)

IG_sym=sum(coefs.*subs(f,x,nodes)) #integrala gauss simbolica, prima formula din lab11_notite -> f se pune fara pondere aici

format long
IG_num=eval(IG_sym) #integrala gauss numeric
IG_num2=gauss_quad_num('Cebisev1',function_handle(f),n)

I_octave=integral(function_handle(f*w),-1,1) #f cu pondere doar cadn verifivam in octave f*w



rest_fara_f=1/factorial(sym(2*n))*int(pi4^2*w,x,-1,1) #formula rest gauss din notite fara f^(2n) (xi) => ce e incercuti cu albastru in notite
err_max=eval(rest_fara_f*e)

#diff(f,x,2*n)
##fplot(function_handle(diff(f,x,2*n)),[-1,1]) #comentat pentru rest







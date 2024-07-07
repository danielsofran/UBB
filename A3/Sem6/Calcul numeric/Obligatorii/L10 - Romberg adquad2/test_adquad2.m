f=@(t) 2/sqrt(pi)*exp(-t.^2)

clf;
fplot(f,[-2,2]) %clopotul lui gaus, erf


format long
ERF=erf(1)
Q=quad(f,0,1)%quadratura
AQ=adquad2(f,0,1)



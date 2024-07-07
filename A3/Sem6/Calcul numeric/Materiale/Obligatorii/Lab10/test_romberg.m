f=@(t) 2/sqrt(pi)*exp(-t.^2)

clf;
fplot(f,[0,1]) %clopotul lui gaus, erf
axis([0,1,0,1.5])

format long

T=romberg(f,0,1,4)
ERF=erf(1)
Q=quad(f,0,1)%quadratura
AQ=adquad2(f,0,1)

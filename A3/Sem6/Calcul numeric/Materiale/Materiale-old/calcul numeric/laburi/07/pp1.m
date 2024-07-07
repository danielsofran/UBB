disp('problema 1')
disp('rezultat Lagrange')
Lagrange([0 1 2], [exp(0) exp(1) exp(2)], 0.25) 

disp('rezultat Hermite')
Hermite([0 1 2], [exp(0) exp(1) exp(2)], [exp(0) exp(1) exp(2)], 0.25)
disp('rezultat e^0.25')
exp(0.25)
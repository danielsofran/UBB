timp = [0 3 5 8 13]
distanta = [0 225 383 623 993]
viteza = [75 77 80 74 72]
t=10
disp('Distanta parcursa la momentul t=10')
d = Hermite(timp, distanta, viteza, t)
disp('Viteza la momentul t=10')
disp(d/t)
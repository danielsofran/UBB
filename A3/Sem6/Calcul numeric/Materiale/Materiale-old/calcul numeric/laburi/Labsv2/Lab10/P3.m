a = 1; 
b = 2;
%f = @(x) sin(x);
f = @(x) 1/x;
e = 0.00001;

disp("Rezultatul metodei de cuadratura adaptiva pentru ")
disp("-----------------------");
disp("metoda trapezului");
t = cuadraturaAdaptivaT(f,a,b,e)
disp("-----------------------");
disp("metodat dreptunghiului");
d = cuadraturaAdaptivaD(f,a,b,e)
disp("-----------------------");
disp("metoda lui Simpson");
s = cuadraturaAdaptivaS(f,a,b,e)

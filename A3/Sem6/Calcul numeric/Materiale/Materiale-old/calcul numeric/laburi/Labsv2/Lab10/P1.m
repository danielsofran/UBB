a = 1;
b = 2;
n = 15;
f = @(x) sin(x);

disp("Formula treptata a ")
disp("-----------------------");
disp("trapezului");
t = trapez(f,a,b,n)
disp("-----------------------");
disp("dreptunghiului");
d = dreptunghi(f,a,b,n)
disp("-----------------------");
disp("lui Simpson");
s = Simpson(f,a,b,n)

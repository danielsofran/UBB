a = 1;
b = 2;
%f = @(x) 1/x;
f = @(x) sin(x);
e = 0.00001;

a = adquad(f,a,b,e)

%f = @(x) 1/x;
f = @(x) sin(x);
e = 0.01;

r = Romberg(f,1,2,4,e)
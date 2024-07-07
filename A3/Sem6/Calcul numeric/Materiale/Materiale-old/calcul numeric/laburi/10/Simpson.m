function [ res ] = Simpson( f, a, b, n )
m = n / 2;
h = (b - a) / (2 * m);
k = 0 : n;
x = a + k * h;

sum1 = 0;
for k = 1 : m - 1
    sum1 = sum1 + f(x(2*k));
end

sum2 = 0;
for k = 1 : m
   sum2 = sum2 + f(x(2*k-1)); 
end
res = ((b - a) / (6 * m)) * (f(a) + f(b) + 2 * sum1 + 4 * sum2);
end

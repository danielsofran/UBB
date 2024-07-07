pkg load statistics;
close all;

N=500;
v=[-2   -1 0   1   2];
p=[0.1 0.4 0 0.3 0.2];

X=randsample(v, N, replacement = true, p);
Y=unifrnd(-1, 4, 1, N);
U=X.^3-Y.^3;

# a
hist(U, 20)

# b
mean(U<0)
mean(U)
var(U)

#c
PT=sum(v.^3 .* p)

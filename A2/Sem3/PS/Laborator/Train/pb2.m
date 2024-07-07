pkg load statistics;
close all;

N = 1000;
int=-2:5;

figure
plot(-2:0.01:5, exppdf(-2:0.01:5, 1/2), 'r-')
xlim([-3 6]);
title("Densitate");

figure

plot(-2:0.01:5, expcdf(-2:0.01:5, 1/2), 'g-')
xlim([-3 6]);
title("Repartitie");

x=exprnd(1/2, 1, N);
[mean(X) std(x)]
mean(x>0.7)
PT=1-expcdf(0.7, 1/2)

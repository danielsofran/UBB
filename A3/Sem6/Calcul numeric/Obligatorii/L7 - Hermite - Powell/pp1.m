syms x;

disp('problema 1')
disp('rezultat Lagrange')
Lagrange([0 1 2], [exp(0) exp(1) exp(2)], 0.25) 

disp('rezultat Hermite')
hermPol = interpolareHermite([0 1 2], [exp(0) exp(1) exp(2)], [exp(0) exp(1) exp(2)]);
rez = double(subs(hermPol, x, 0.25))
disp('rezultat e^0.25')
exp(0.25)

nodes = [0 1 2];
nodevals = exp(nodes);
t = 0 : 0.01 : 2;
res = double(subs(hermPol, x, t));

figure; hold on;
plot(t, res, 'k', "MarkerSize", 8);
%plot(t, exp(t), 'b');
plot(nodes, nodevals, 'rx');

resl = LagrangeMaiMultePuncte(nodes, nodevals, t);
plot(t, resl, 'g');
legend('Hermite', 'Points', 'Lagrange');

hold off;
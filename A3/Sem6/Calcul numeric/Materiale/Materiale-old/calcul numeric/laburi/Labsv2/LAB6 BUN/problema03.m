nodes = [1, 1.8, 2.2];
nodevals = exp(nodes);

t = 1 : 0.01 : 3;
res = LagrangeMaiMultePuncte(nodes, nodevals, t);

plot(t, res, 'green');
hold on;

resexp = exp(t);
plot(t, resexp);
hold off;
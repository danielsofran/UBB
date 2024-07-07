nodes = [1.1 1.2 1.7 1.8 3];
nodevals = exp(nodes);

t = 1 : 0.01 : 2;
res = HermiteMaiMultePuncte(nodes, nodevals, nodevals, t);
plot(t, res, 'color', rand(1,3));
hold on;
resexp = exp(t);
plot(t, resexp, 'color', rand(1,3));
hold off;
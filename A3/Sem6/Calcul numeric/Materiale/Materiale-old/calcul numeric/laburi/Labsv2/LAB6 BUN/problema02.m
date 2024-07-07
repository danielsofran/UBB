nodes = [-5 -4 -3 -2 -1 0 1 2 3 4 5];
m = 11;

labels = {};
for k = 1 : m
    t = -5 : 1 : 5;
    p = polinomFundamentalMaiMultePuncte(nodes, k, t);
    labels = [labels num2str(k)];
    plot(t, p, 'color', rand(1,3))
    hold on;
end
legend(labels);
hold off;
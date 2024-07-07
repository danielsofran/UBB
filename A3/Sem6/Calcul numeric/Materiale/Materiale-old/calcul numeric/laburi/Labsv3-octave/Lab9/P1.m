X = 1:20;
Y = sin(X);

% datele ce urmeaza a fi aproximate
x = X(1):(X(end)-X(1))/100:X(end);

y = mcmmp_discreta(x, X, Y, 5);

plot(X, Y, 'o', x, y, '-');
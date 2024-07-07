x = [-3 3];
f = [6 6]; 
fd = [-2.5 4];
t = -2 : 0.01 : 2;
res = HermiteMaiMultePuncte(x, f, fd, t);
plot(t, res, 'color', rand(1,3));

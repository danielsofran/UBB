X=linspace(-1, 1, 1000);
Y=Newton_interpolare([-1,1,1/2], [1,2,-1],X);
plot(X,Y)
hold on
plot([-1, 1, 1/2], [1,2,-1], '*r')

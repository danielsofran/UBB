timpi=[0 3 5 8 13];
distante=[0  225  383  623  993];
viteze=[75  77   80   74   72];
syms X
X=linspace(0,13,1000)
%Y=Hermite_interpolare(timpi, distante, viteze, X)
%plot(X,Y)% timpi, distanta
[Y,dY]=Hermite_interpolare(timpi, distante, viteze, X)
plot(Y,dY);% timpi, viteza
hold on;
plot(distante,viteze,'*r')




%model polinomial de grad 3: y=c0 + c1 * t + c2* (t^2) + c3 * (t^3)
%model exponential: y = K * (e^?t);

%populatia
  Y = [75.995 91.972 105.711 123.203 131.669 150.697 179.323 203.212 226.505 249.633 281.422 308.786]';

  X = (1900:10:2010)'; %anii de recensamant
  xg = (1890:1:2019)'; %anii de evaluare
  x = [1975 2015];     %anii de predictie

  c = polyfit(X,Y,3);

  m = mean(X); 
  sd = std(X);
  Xn  = (X-m)/sd;
  xgn = (xg-m)/sd;
  xn  = (x-m)/sd;
  %coeficienti normalizati
  cn = polyfit(Xn,Y,3);
  %pedictia populatiei
  yg = polyval(cn,xgn);
  y  = polyval(cn,xn); 

  plot(X,Y,'o',xg,yg,'-',x,y,'*')
  for i = 1:length(x)
    text(x(i),y(i)-20,num2str(y(i)))
  end
  
  title('Populatia SUA', 'FontSize', 14)
  xlabel('an', 'FontSize', 12)
  ylabel('milioane', 'FontSize', 12)
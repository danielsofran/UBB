function modelEliptic ()

  [x,y] = date_input();

  A = [y.^2, x.*y, x, y, ones(size(x))];
  b = x.^2;

  coef = A\b;
  a=coef(1); b=coef(2); c=coef(3); d=coef(4); e=coef(5);
  disp("Coeficientii:");
  disp([a,b,c,d,e]);
  
  u=linspace(min(x),max(x),20);
  v=linspace(min(y),max(y),20);
  [X,Y]=meshgrid(u,v);
  Z=a*Y.^2+b*X.*Y+c*X+d*Y+e-X.^2;
  
  figure(1);
  contour(X,Y,Z,[0,0]); hold on
  plot(x,y,'ro'); hold on

  figure(2)
  plot(x,y,'ro'); hold on
  
  u=linspace(-2,2,40);
  v=linspace(-3,0,40);
  [X,Y]=meshgrid(u,v);
  Z=a*Y.^2+b*X.*Y+c*X+d*Y+e-X.^2;
  contour(X,Y,Z,[0,0]);

  err=norm(a*y.^2+b*x.*y+c*x+d*y+e-x.^2);
  disp(err);

end

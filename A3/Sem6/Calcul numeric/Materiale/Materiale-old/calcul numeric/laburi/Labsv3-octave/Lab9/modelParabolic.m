function modelParabolic ()
  
  [x,y]=date_input();
  
  A=[y,ones(size(x))];
  b = x.^2;

  coef=A\b;
  a=coef(1); e=coef(2);
  disp("Coeficienti:");
  disp([a,e]);
  
  u=linspace(min(x),max(x),20);
  v=linspace(min(y),max(y),20);
  [X,Y]=meshgrid(u,v);
  Z=a*Y+e-X.^2;
  
  figure(3);
  contour(X,Y,Z,[0,0]); hold on
  plot(x,y,'ro')

  figure(4)
  plot(x,y,'ro'); hold on
  
  u=linspace(-2,2,40);
  v=linspace(-3,0,40);
  [X,Y]=meshgrid(u,v);
  Z=a*Y+e-X.^2;
  contour(X,Y,Z,[0,0]);

  err=norm(a*y+e-x.^2);

  disp(err);
end

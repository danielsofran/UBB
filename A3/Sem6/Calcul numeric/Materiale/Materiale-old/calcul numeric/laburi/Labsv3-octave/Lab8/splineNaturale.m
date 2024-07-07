% X   - punctele date
% Y   - valorile punctelor date
% x   - punct aproximat
% y - aproximare pentru x
% c - coeficientii ecuatiei
function [c,y] = splineNaturale(X,Y,x)
  
  n = length(X);
  [CN,b,index] = ecuatieSpline(X,Y,x);
  
  CN(index,3) = 2;
  index++;
  CN(index,4*(n-1)-1:4*(n-1)) = [2 6*(X(n)-X(n-1))];
  
  c = CN \ b;
  c = c';

  y = interpolareSpline(x,X,c);
end
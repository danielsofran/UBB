
% F(X) = Y F(x) ~ y
% X   - punctele date
% Y   - valorile punctelor date
% x   - punct aproximat
% yd1 - y'(x(1))
% ydn = y'(x(length(x))
% y - aproximare pentru x
% c - coeficientii ecuatiei
function [c,y] = splineComplete(X,Y,x,yd1,ydn)

  [CN,b,index] = ecuatieSpline(X,Y,x);
  n = length(X);
  
  CN(index,2) = 1;
  b(index)   = yd1;
  index++;

  CN(index,4*(n-1)-2:4*(n-1)) = ((X(n)-X(n-1)) * [1 2 3]) .^ [0 1 2];
  b(index) = ydn;

  #coeficientii polinoamelor spline
  c = CN \ b;
  c = c';

  y = interpolareSpline(x,X,c);
end
% X   - punctele date
% Y   - valorile punctelor date
% x   - punct aproximat
% y - aproximare pentru x
% c - coeficientii ecuatiei
function [c,y] = splineDeBoor(X,Y,x)
  
  n = length(X);
  [CN,b,index] = ecuatieSpline(X,Y,x);
  
  CN(index,4) = 6;
  CN(index,8) = -6;  
  index++;
  
  CN(index,4*(n-2)) = 6;
  CN(index,4*(n-1)) = -6;
  
  #coeficientii polinoamelor spline
  c = CN \ b;
  c = c';
  
  y = interpolareSpline(x,X,c);
end
%# Copyright (C) 2020 Damaris
%#

% X   - punctele date
% Y = f(X)
% Yd - derivata lui Y
% z - nodurile duble in punctele X.
% Q - derivata in noduri;
function [z,Q] = noduriDuble (X,Y,Yd)
  
  m = length(X);
  for i=1:m
    z(2*i-1)    = z(2*i)   = X(i);
    Q(2*i-1,1)  = Q(2*i,1) = Y(i);
    Q(2*i,2)    = Yd(i);
    if (i ~= 1)
      num   = Q(2*i-1,1) - Q(2*i-2,1);
      denom = z(2*i-1)   - z(2*i-2);
      Q(2*i-1,2) = num/denom;
    endif
  endfor
  
  for i=3:2*m
    for j=3:i
      num    = Q(i,j-1) - Q(i-1,j-1);
      denom  = z(i)     - z(i-j+1);
      Q(i,j) = num / denom;
    endfor
  endfor
  Q = diag(Q);
  Q = Q';

endfunction

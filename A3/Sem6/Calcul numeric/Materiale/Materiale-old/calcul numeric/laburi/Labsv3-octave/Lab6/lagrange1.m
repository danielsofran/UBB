%Interpolarea Lagrange
%x - punctele
%X - nodurile
%Y - valorile functiei in noduri
function y = lagrange1(x,X,Y)
  
  y = zeros(1,length(x));
  c = columns(X);
  
  for i = 1 : c
   f = Y(1, i);
   
  
   for j = 1 : c
      u = 1; d = 1;
      if j ~= i
          u = u * (x(j) - X(1, j)); % x - xj
          d = d * (X(1, i) - X(1, j)); % xk - xj
      endif
       y(i) = y(i) + f * (u / d);
   endfor
  
  endfor

endfunction

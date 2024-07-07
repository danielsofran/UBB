% X -  noduri
% Y -  valorile functiei in noduri
% x -  punctele
% y -  polinomul rezultat
function y = barycentric(x,X,Y)
  
  n = length(X);
  c = ones(1,n);
  for j=1:n
      c(j)= prod(X(j) - X([1:j-1,j+1:n]));
  end
  c=1./c;
  
  numer = zeros(size(x));
  denom = zeros(size(x));
  exact = zeros(size(x));

  for j=1:n
      xdiff = x-X(j);
      
      temp = c(j) ./ xdiff;
      numer += temp * Y(j);
      denom += temp;
      
      exact(xdiff == 0) = j;
  end
  
  y = numer ./ denom;
  
  jj = find(exact);
  y(jj) = Y(exact(jj));
end
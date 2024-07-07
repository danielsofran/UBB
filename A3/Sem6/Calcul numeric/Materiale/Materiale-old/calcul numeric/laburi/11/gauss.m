function [n,c] = gauss (a,b)
  
  n = length(a);
  sqrtvar = sqrt(b(2:n));
  Jn = diag(a) + diag(sqrtvar,-1) + diag(sqrtvar,1);
  [v,w] = eig(Jn);
  
  n = diag(w);  %noduri
  c = b(1)*v(1,:).^2;  %coeficienti

end

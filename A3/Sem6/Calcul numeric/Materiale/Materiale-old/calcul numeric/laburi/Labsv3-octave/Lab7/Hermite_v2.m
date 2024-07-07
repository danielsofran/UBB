% x - aproximarea
% X   - punctele date
% Y = f(X)
% Yd - derivata lui Y
% H - valorile polinomului Hermite
% y - functia aproximata
function [H,y] = Hermite_v2(x,X,Y,Yd)
  H = [];
  
  [z,Q] = noduriDuble(X,Y,Yd);
  # stergerea Y(1) din Q
  Q = Q(2:end);
  py = Y(1);
  H(1) = Y(1);
  
  for(i=1:length(Q))
    y = py + Q(i)*prod(x-z(1:i)); 
    
    if(abs(y-py) < eps) 
      return;
    end
    
    H(i+1) = py = y;
  end
end
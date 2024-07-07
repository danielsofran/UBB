function [L,U,P] = descLUP (A)
  
  [n,m] = size(A); %n - nr randuri; m - nr coloane

  I = eye(n); % matricea unitate cu dimensiunea nxn 
  U = A;
  L = I;
  P = I;
  
  for k = 1: n - 1
    
    %Alege i ? k care maximizeaza |uik|;
    [valoare, i] = max(A(k:m,k));
    i = i+k-1;
    
    if(k ~= i)
      
      %interschimbare U
      aux = U(k,k:m);
      U(k,k:m) = U(i,k:m);
      U(i,k:m) = aux;
      
      %interschimbare L
      aux = L(k,1:k-1);
      L(k,1:k-1) = L(i,1:k-1);
      L(i,1:k-1) = aux;
      
      %interschimbare P
      aux = P(k,:);
      P(k,:) = P(i,:);
      P(i,:) = aux;      
    end
    
    %Complementul Schur
    %disp("Complement");
    for j = k+1:n
      L(j,k)   = U(j,k)/U(k,k);
      U(j,k:m) = U(j,k:m) - L(j,k) * U(k,k:m);
    end
  end

end
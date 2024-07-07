%Eliminare gaussiana cu pivotare partiala
function [A,x] = GaussPP (A, B, n)
  x=zeros(n,1);
  for p=1:n-1
    ip = detP(A,p,n);
    if ip == 0 
      error("nu exista solutie unica");
      break
    end
    x(p) = ip;
    
    for j=p:n 
      aux = A(p,j);
      A(p,j) = A(ip,j);
      A(ip,j) = aux;      
    end
    
    for i=p+1:n
      A(i,p) = A(i,p)/A(p,p);      
    end
    
    for i=p+1:n
      for j=p+1:n
        A(i,j) = A(i,j) - (A(i,p)/A(p,p))*A(p,j);      
      end
    end
  end
  
  
end

%metoda lui Jacobi: M = D, N = L+U. In acest caz, T = (D^1)*(L+U), c = (D^(-1))*b.
function x_previous = jacobi(A,b)

  no_interation = 500;
  %interatia curenta
  i = 0;
  % n - lungimea matricei solutie 
  n = length(b);
  x_previous = zeros(n,1);
  x_current = zeros(n,1);
  
  M = diag(diag(A));
  N = M - A;
  
  while i <= no_interation
    i=i+1
    x_previous = x_current;
    x_current   = M\(N*x_previous+b);
    
    if norm(x_current-x_previous,inf)/norm(x_current,inf) < eps
      break;
    end
  end
end
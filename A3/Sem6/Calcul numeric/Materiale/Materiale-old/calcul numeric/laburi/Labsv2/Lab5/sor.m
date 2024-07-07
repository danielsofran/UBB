function x_old = sor(A,b)
    
    no_interations = 1000;
    %#current iteration
    i=0;
    
    %# n - lungimea matricei solutie 
    n = length(b);
    x_old = zeros(n,1)
    x_current = zeros(n,1)
    
    D = diag(diag(A));
    L = D - tril(A);
    U = D - triu(A);
    
    %#omega:
    Tj = D \ (L+U);
    sr = max(abs(eig(Tj)));
    w = 2/(1+sqrt(1-sr^(2)));
  
    T = (D-w*L)^(-1) * ((1-w)*D + w*U);
    c = (D-w*L)^(-1)*w*b;
    
    while i <= no_interations
        i=i+1
        x_old = x_current;
        x_current = T * x_old + c;
        norm_current = norm(x_current-x_old, inf);
        
        if norm_current < eps
          break;
        end
    end
    
end
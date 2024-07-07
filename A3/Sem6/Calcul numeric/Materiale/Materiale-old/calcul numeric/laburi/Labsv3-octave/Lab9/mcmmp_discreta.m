
%aproximarea discreta prin metoda celor mai mici patrate
function y = mcmmp_discreta(x, X, Y, n)
   
   y = zeros(1,length(x)); 
   
   
   %aflare coeficient
   A = zeros(n+1,n+1);
   b = zeros(1,n+1);
   for i=1:n+1
     for j=1:n+1
       A(i,j) = sum(X.^(j-1) .* X.^(i-1));
     end
     b(i) = sum(Y .* X.^(i-1));
     end
   C = A \ b';
   c = flip(C');
   
   
   for i=1:length(x)
     y(i) = polyval(c,x(i));
   end
   
end

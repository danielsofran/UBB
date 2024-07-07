function [A,b] = mddSystem (n)
  
  A = rand(n);
  A = A + A';
  A = A + max(sum(A,2))*eye(n);
  b = A * [1:n]';

end

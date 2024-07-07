function [A,B] = mySystems (n)
  
  A = rand(n);
  while det(A) == 0
    A = rand(n);
  end
  
  B = sum(A,2);
  
end

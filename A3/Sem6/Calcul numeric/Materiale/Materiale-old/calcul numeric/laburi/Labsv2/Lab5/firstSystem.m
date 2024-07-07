function [A,b] = firstSystem (n)
  
  A = zeros(n);
  for(i=1:n)
    A(i,i) = 5;
    if(i-1 > 0)
      A(i,i-1) = -1;
    end
    if(i+1 <= n)
      A(i,i+1) = -1;  
    end 
  end
  
  b = zeros(n,1);
  b(1,1) = 4;
  b(n,1) = 4;
  for i=2:n-1
    b(i,1) = 3;
  end

end

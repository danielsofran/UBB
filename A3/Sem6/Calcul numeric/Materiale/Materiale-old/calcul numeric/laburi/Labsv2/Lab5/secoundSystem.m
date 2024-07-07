function [A,b] = secoundSystem (n)

  A = zeros(n);
  for(i=1:n)
    A(i,i) = 5;
    if(i-1 > 0)
      A(i,i-1) = -1;
    end
    if(i+1 <= n)
      A(i,i+1) = -1;  
    end
    if(i-3 > 0) 
      A(i,i-3) = -1;
    end
    if(i+3 <=n)
      A(i,i+3) = -1;
    end  
  end
%#A 
  b = zeros(n,1);  
  b(1,1) = 3;
  b(n,1) = 3;
  
  if n <=6 
    for i=2:n-1
      b(i,1) = 2;
    end
  else
    b(2,1) = 2;
    b(3,1) = 2;
    b(n-2,1) = 2;
    b(n-1,1) = 2;  
    for i=4:n-3
      b(i,1) = 1;
    end
  end
%  #b

end

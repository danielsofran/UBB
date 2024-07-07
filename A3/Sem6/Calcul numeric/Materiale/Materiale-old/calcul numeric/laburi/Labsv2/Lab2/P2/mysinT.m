
function [sin,N] = mysinT (x, err)

  x = mod(x,2*pi);
  sin = x;
  current = -x^2/factorial(3);
  N = 2; 
  
  while abs(current) > err
    sin = sin + current;
    current = (-1)^N * x^(2*N+1) / factorial(2*N+1);
    N = N+1;    
  end

end

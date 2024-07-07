
function [cos,N] = mycosT (x, err)

  x = mod(x,2*pi);
  cos = 1;
  current = -x^2/2;
  N = 2;
  
  while abs(current) > err
    cos = cos + current;
    current = (-1)^N * x*(2*N) / factorial(N*2);
    N = N + 1;  
  end

end

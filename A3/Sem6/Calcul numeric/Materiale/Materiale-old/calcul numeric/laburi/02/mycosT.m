
function [cos,N] = mycosT (x, err)
  syms z
  x = mod(x,2*pi);
  if(x>pi/4 &&  x<pi/2)
      [cos,N]=mysinT(pi/2-x,err);
  elseif(x >= pi/2 && x < pi)
      [z,N]=mysinT(x-pi/2,err);
      cos=-z;
  elseif(x >= pi && x < 3*pi/2)
      [z,N]=mycosT(x-pi,err);
      cos=-z;
  elseif(x >= 3*pi/2)
      [cos,N]=mysinT(x-3*pi/2,err); 
  else
    cos = 1;
    current = -x^2/2;
    N = 2;
    while abs(current) > err
        cos = cos + current;
        current = (-1)^N * x*(2*N) / factorial(N*2);
        N = N + 1;  
    end
  end
end

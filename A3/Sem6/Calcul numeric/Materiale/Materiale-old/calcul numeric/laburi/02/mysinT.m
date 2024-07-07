
function [sin,N] = mysinT (x, err)
  syms z
  x = mod(x,2*pi);
  if(x>pi/4 &&  x<pi/2)
     [sin,N]=mycosT(pi/2-x,err);
  elseif(x >= pi/2 && x < pi)
      [sin,N]=mycosT(x-pi/2,err);
  elseif(x >= pi && x < 3*pi/2)
      [z,N]=mysinT(x-pi,err);
      sin=-z;
  elseif(x >= 3*pi/2)
      [z,N]=mycosT(x-3*pi/2,err);
      sin=-z;
  else
      sin = x;
      current = -x^2/factorial(3);
      N = 2; 

      while abs(current) > err
        sin = sin + current;
        current = (-1)^N * x^(2*N+1) / factorial(2*N+1);
        N = N+1;    
      end
  end
end

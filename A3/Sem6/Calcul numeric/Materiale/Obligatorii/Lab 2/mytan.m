function y=mytan(x,digits=1000)
  x=reducereper(x,digits);
  semn=1;
  if x>3*pi/2 #cadran 4
      x=2*pi-x;
      semn=-1;
  elseif x>pi #cadran 3
      x=x-pi;
      semn=-1;
  elseif x>pi/2 #cadran 2
      x=pi-x;
  end
  if x<=pi/4 # cadran 1
      y=tanred(x);
  else
      y=1/tanred(pi/2-x);
  end
  y=semn*y;

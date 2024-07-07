function P3 ()
  
  f = @(x)sin(x);
  X = [ 0, pi/6, pi/4, sqrt(pi)/2, pi];
  Y = f(X);
  
  x = linspace(0,2*pi,50); #Return a row vector with 50 linearly spaced elements between 0 and pi.
  y = barycentric(x,X,Y);
  
  plot(x, y, 'r');
  hold on
endfunction

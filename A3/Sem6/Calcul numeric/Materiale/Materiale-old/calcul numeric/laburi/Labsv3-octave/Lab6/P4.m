function P4 ()

  x = [3.8, 4.2, 5];
  f = @exp;
  m = 3;
  X = [3 4 5]; #nodurile
  Y = f(X);

  y = lagrange1(x,X,Y)

endfunction

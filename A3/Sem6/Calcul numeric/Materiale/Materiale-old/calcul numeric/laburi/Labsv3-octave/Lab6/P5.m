function P5 ()
  
  X = [81 100 121 144];
  Y = [9 10 11 12];
  x = [115];

  expected = interp1(X, Y, x)
  y = barycentric(x,X,Y)
endfunction

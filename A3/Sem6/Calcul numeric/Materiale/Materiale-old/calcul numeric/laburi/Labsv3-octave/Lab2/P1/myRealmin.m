


function realmin = myRealmin ()
  
  x = 1;
  while (0 + x/2) ~= 0
    x = x/2;
  end
  
  aux = x;
  while (x + aux) ~= x
    x = x*2;
  end
  
  realmin = x/2;
end


function realmax = myRealmax ()
  x = 1-myeps();
  
  while (2*x) ~= x
    realmax = x;
    x = x*2;
  end
end

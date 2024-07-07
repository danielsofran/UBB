
function r = sinDeriv (x)
  m = mod(x,4);
  if m == 0 || m == 2
    r = 0;
    return;   
  end
  if m == 1
    r = 1;
    return;
  end
  if m == 3
    r = -1;
    return;
  end
end


function r = cosDeriv (x)
  m = mod(x,4);
  if m == 1 || m == 3
    r = 0;
    return;
  end
  if m == 0
    r = 1;
    return;
  end
  if m == 2
    r = -1;
  end
end

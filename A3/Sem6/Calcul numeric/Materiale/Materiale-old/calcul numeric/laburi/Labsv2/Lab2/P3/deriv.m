
function d = deriv (typeF, x)
  
  if strcmp(typeF, "sin") == 1
    d = sinDeriv(x);
    return
  end
  
  d = cosDeriv(x);
end

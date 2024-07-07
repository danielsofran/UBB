function p = detP (A, i, n)
  %find a A(p,i)!=0
  p = i;  
  while (true)
    if A(p,i) ~= 0
      break
    end
    if i+1 > n
      break
    end
    p = p+1;
  end
 
  max = A(p,i);
  for j=1:n  %j <= n+1 ????
    if A(j,i) > max
      max = A(j,i);
      p = j;
    end
  end
end

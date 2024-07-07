%En = integral((x^n)*exp(n-1)): 0 - 1
%E1 = 1/e
%En = 1 - n*E(n-1)
function [e,n] = prob5 (err=1e-10)
  n = 10;
  while abs(myE(n+1)-myE(n))>err
      n=n+1;
  end
  #n
  e=MyE(n);
endfunction

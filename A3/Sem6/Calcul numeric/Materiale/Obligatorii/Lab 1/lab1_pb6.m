pkg load symbolic

order = 1;
R = @(k) (4 / (2*k+3));
while (abs(R(order)) >= 1e-05)
  order+=1;
endwhile
order

syms x;
ty = taylor(atan(x), x, 0, 'order', order);
estimation = double(subs(ty, x, 1));

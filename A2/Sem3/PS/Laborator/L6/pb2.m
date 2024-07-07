function pb2n(n=1000)
  g1 = @(x) exp( - x .^ 2), a = -2, b = 2, M = 1
  g2 = @(x) abs(sin(exp(x))), a = -1, b = 3, M = 1
  g3 = @(x)  x .^2 ./ (1 + x .^ 2) .* (x <= 0) + sqrt(2 * x - x .^ 2) .* (x > 0), a = -1, b = 2, M = 1
  % g3 - functie pe ramuri

  % aleg un g din g1, g2, g3
  g = g3, a = -1, b = 2, M = 1
  clf; hold on;
  x=unifrnd(a, b, 1, n);
  y=unifrnd(0, M, 1, n);
  plot(x(y<=g(x)), y(y<=g(x)), 'r*')
  plot(x(y>g(x)), y(y>g(x)), 'b*')
  t=linspace(a, b, n);
  plot(t, g(t), 'k-')
endfunction

pb2n()

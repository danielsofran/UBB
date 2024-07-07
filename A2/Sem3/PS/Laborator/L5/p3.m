function ex3(N = 500)
  clf;
  t = linspace(0, 2*pi, 360);
  polar(t, 4*ones(1, 360), 'w');
  hold on;

  [X, Y] = boxmuller(N);
  plot(X, Y, 'r*');
  polar(t, 0.5*ones(1, 360), 'k');

  sum(X .^ 2 + Y .^ 2 < 0.5 .^ 2) / N # (x - cx).^2 + (y - cy).^2 < r .^ 2
  % pentru frecventa relativa, putem sa folosim si mean(x .^ 2 + Y .^ 2 < 0.25)

  1 - exp(-1/8) # teoretica

  % X = R .* cos(V)
  % Y = R .* sin(V)
  % P(X .^ 2 + Y .^ 2 < 0.25) = P(-2 * log(U(1, :)) < 0.25)
  % P(log(U(1, :)) > -1 / 8) = P(U(1, :) > exp( -1 / 8 ))
  % 1 - P(U(1, :) < exp(-1/8))

  Z = normrnd(0, 1, 2, N);
  plot(Z(1, :), Z(2, :), 'c*');
  mean(Z(1, :) .^ 2 + Z(2, :) .^ 2 < 0.25)

endfunction

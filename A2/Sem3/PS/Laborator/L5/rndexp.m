function X = rndexp(lambda, N)
  X = -log(1 - rand(1, N)) / lambda;
endfunction

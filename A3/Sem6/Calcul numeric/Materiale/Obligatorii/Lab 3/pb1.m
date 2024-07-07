function pb1
  disp('Hilbert')
  for n=10:15
    cond(hilb(n))
  endfor
  disp('Vandermond_a')
  for n=10:15
    cond(vander(linspace(-1,1,n)),1)
  endfor
  disp('Vandermond_b')
  for n=10:15
    cond(vander(1./(1:n)),1)
  endfor
endfunction

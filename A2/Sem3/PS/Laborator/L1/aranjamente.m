function retval = aranjamente (v, k)
  w = nchoosek(v, k);
  [linii, ~]=size(w)
  for ind=1:linii;
    perms(w(ind,:))
  endfor
endfunction

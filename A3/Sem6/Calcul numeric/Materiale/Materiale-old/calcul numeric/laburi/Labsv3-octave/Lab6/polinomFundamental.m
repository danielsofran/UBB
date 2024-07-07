function pol = polinomFundamental (v_nodes, g, x)
  
  c = columns(v_nodes);
  u = 1;
  d = 1;
  
  for i = 1:c
    if i ~= g
      u = u * (x - v_nodes(1, i));
      d = d * (v_nodes(1, g) - v_nodes(1, i));
    endif
  endfor

  pol = u / d;
endfunction

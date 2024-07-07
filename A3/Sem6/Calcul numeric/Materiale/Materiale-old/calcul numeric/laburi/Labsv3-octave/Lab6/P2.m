function P2 ()
  
  v_nodes = [ 1 1.3 1.5 1.7 1.9 ]
  l = {}; #labels
  
  for g = 1:5
    x = 1 : 0.01 : 2;
    pol = zeros(size(x));
    c = columns(x);
    for i =1:c
      pol(1,i) = polinomFundamental(v_nodes,g,x(1,i));
    endfor
    
    l = [l num2str(g)];
    plot(x,pol,'color',rand(1,3))
    hold on;
  endfor
  
  legend(l);
  hold off;
  

endfunction

function lab2_pb3()
  x=pi;
  digits=1144;
  m=6;
  k=8;

  disp("Result");
  disp("cos");
  my_better_cos_pade(x,m,k,digits)
  disp("sin");
  my_better_sin_pade(x,m,k,digits)
endfunction

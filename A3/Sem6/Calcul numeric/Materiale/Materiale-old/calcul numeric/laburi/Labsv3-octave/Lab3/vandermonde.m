
function [] = vandermonde (n, tip_t)
  
  fprintf("Pentru n= %d \n",n);
  
  %vectorul t al matricii Vandermonde
  t = [];
  for i = 1:n
    if strcmp(tip_t,"a") == 1 %pentru tk = -1+k*(2/n) 
      x = -1 + i * (2/n);
    else if strcmp(tip_t,"b") == 1 % pentru tk = 1/k
      x = 1/i;
      else
        error("tip_t poate fi doar a / b !!");
        end
    end       
    t = [t x];
  end
  
  %matricea Vandermode
  v = [];
  for i = 1:n
    row = [];
    for j = 1:n
      x = t(j)^(i-1);
      row = [row x];
    end
    v = [v; row];
  end
  
  fprintf("Coditionarea: ");
  c = cond(v)

  fprintf("Norma Cebiseu: ");
  norma_c = cond(v,inf)

end

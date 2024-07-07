
function [] = prob2_hilbert ()
  
  for i=10:15
    %matricea Hilbert de grad i
    h = hilb(i);
    %format rat
    %format long
  
    %coditionarea matricii
    fprintf("Conditionarea matricii Hilbert de grad %d : ",i);
    cond(h)
  
    %norma Euclidiana
    fprintf("Norma euclidiana a matricii Hilbert de grad %d: ",i);
    norm_e = norm(h,2)  
    fprintf("\n");
  end
  
  end

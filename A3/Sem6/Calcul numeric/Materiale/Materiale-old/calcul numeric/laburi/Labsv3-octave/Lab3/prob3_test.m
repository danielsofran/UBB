function [] = prob3_test ()
  
  for i=10:15
      disp("\nPentru a): ");
      vandermonde(i,"a");
      fprintf("\nPentru b): ");
      vandermonde(i,"b"); 
  end

end

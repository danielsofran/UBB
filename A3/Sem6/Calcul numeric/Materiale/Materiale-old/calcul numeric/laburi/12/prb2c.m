%# Copyright (C) 2020 Damaris
%#
%func�tie care calculeaza sqrt(a) cu precizia epsilon-ul ma�sinii
function prb2c()
    
    l = 0.5;
    x0 = 1;
    a=10;
    f = @(x) x^(2-l) - a*x^(-l);
    df = @(x) (2-l)*x^(1-l)+a*l*x^(-l-1);
    
    [z,nr_it] = Newton(f,df,x0,eps);
    disp("Aproximarea:");
    disp(z);
    disp("Pentru nr de iteratii"); disp(nr_it);
   
end
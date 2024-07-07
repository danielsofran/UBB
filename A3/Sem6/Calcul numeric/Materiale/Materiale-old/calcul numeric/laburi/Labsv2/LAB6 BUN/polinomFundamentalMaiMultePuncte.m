function [val] = polinomFundamentalMaiMultePuncte(nodes, k, x)
    val = zeros(size(x));
    [~, c] = size(x);
    for i = 1 : c 
       val(1, i) = polinomFundamental(nodes, k, x(1, i)); 
    end
end

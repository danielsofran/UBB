
function y = interpolareSpline(x,X,c)

  n = length(x);
  for(k=1:n)
     xk = x(k);
     n = length(X);
     for optim = 1:n-1
        if X(optim) <= xk && xk <= X(optim+1)
			      break;
		    endif
	   endfor
     y(k) = sum( ((xk-X(optim)) * ones(1,4)) .^ [0 1 2 3] .* c(4*optim-3:4*optim));
  endfor
  
endfunction
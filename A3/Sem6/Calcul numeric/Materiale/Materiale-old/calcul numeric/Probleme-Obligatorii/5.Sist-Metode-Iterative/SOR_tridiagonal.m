function [x, iterations] = SOR_tridiagonal(d0, d1, d_1, b, omega, error)
    n = length(d0);
    x = zeros(n, 1);
    iterations = 0;
    norm_diff = inf;
    
    while norm_diff > error
        x_prev = x;
        
        for i = 1:n
            if i == 1
                x(i) = (1 - omega) * x_prev(i) + (omega / d0(i)) * (b(i) - d1(i) * x_prev(i+1));
            elseif i == n
                x(i) = (1 - omega) * x_prev(i) + (omega / d0(i)) * (b(i) - d_1(i-1) * x(i-1));
            else
                x(i) = (1 - omega) * x_prev(i) + (omega / d0(i)) * (b(i) - d_1(i-1) * x(i-1) - d1(i) * x_prev(i+1));

            end
        end
        
        norm_diff = norm(x - x_prev);
        iterations = iterations + 1;
    end
end

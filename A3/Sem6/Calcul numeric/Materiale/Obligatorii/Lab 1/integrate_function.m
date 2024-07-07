function result = integrate_function(x)
    f = @(t) 1/(1 + t^2);
    result = quad(f, 0, x);
end

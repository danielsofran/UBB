T = taylor(atan(x), 'order', 200);
my_pi = double(4*subs(T, 1))
abs(pi - my_pi)

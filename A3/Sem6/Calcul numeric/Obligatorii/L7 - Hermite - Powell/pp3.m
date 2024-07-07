
%disp('Distanta parcursa la momentul t=10')
%d = Hermite(timp, distanta, viteza, t)
%disp('Viteza la momentul t=10')
%disp(d/t)
%v = Hermite(timp, viteza, [0 acc], t)


% Given data
timp = [0 3 5 8 13];
distanta = [0 225 383 623 993];
viteza = [75 77 80 74 72];
acc = [0.66 1.5 -2 -0.4];
t = 10;

% Compute distance and velocity using Hermite interpolation
d_pol = interpolareHermite(timp, distanta, viteza);
v_pol = interpolareHermite(timp, viteza, [0 acc]);
d = Hermite(timp, distanta, viteza, t);
v = Hermite(timp, viteza, [0 acc], t);

% Display results
syms x;
disp('Distanta parcursa la momentul t=10:');
disp(d); disp(double(subs(d_pol, x, t)));
disp(d/t);
disp('Viteza la momentul t=10:');
disp(d); disp(double(subs(v_pol, x, t)));
disp(v);


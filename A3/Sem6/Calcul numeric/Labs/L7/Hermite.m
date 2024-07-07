 function [ res ] = Hermite( x, f, fd, point )
    [~, m] = size(x);
    q = zeros(2 * m + 2, 2 * m + 2);
    z = zeros(2 * m + 2);
   
    for i = 1 : m
        z(2 * i - 1) = x(i);
        z(2 * i) = x(i);
        q(2 * i - 1, 1) = f(i);
        q(2 * i, 1) = f(i);
        q(2 * i, 2) = fd(i);
        if i ~= 1
            q(2*i-1,2)=(q(2*i-1,1)-q(2*i-2,1))/(z(2*i-1)-z(2*i-2));
        end
    end
    for i = 3 : 2 * m
       for j = 3 : i
          q(i,j)=(q(i,j-1)-q(i-1,j-1))/(z(i)-z(i-j+1)); 
       end
    end
    s = 1;
    p = q(1, 1);
    for i = 2 : 2 * m
       a = abs(x(floor(i/2)) - point);
       s = s * a;
       prev = p;
       p = p + s * q(i,i);
       res = p;
       if prev-p<1e-5
          break 
       end
    end
end

% function [polinom] = interpolareHermite(noduri, valori_f, valori_deriv)
%     % puncte_evaluare - punctele în care se face evaluarea
%     % noduri - nodurile
%     % valori_f - valorile funcției în noduri
%     % valori_deriv - valorile derivatei funcției în noduri
% 
%     m = length(noduri) - 1;
%     n = 2 * m + 1;
% 
%     % Calculul diferențelor divizate
%     Q = zeros(n+1, n+1);
%     z = zeros(n+1);
%     
%     for i = 1:m+1
%         z(2*i - 1) = noduri(i);
%         z(2*i) = noduri(i);
%         Q(2*i - 1, 1) = valori_f(i);
%         Q(2*i, 1) = valori_f(i);
%         Q(2*i, 2) = valori_deriv(i);
%         if i ~= 1
%             Q(2*i - 1, 2) = (Q(2*i - 1, 1) - Q(2*i - 2, 1)) / (z(2*i - 1) - z(2*i - 2));
%         end
%     end
% 
%     for i = 3:n+1
%         for j = 3:i
%             Q(i, j) = (Q(i, j - 1) - Q(i - 1, j - 1)) / (z(i) - z(i - j + 1));
%         end
%     end
%     % Construirea polinomului interpolator Hermite
%     syms x;
%     polinom = construiestePolinomHermite(diag(Q), z);
% end



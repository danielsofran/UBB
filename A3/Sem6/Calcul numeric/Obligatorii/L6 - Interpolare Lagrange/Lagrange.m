function [ val ] = Lagrange( nodes, nodevals, point )
val = 0;
[lin, col] = size(nodes);
m = col; % nr de puncte in care s-o definit problema
x = point; % punctul pentru care se aproximeaza rezultatul
for k = 1 : m
   f = nodevals(1, k); % rezultatul functiei in nodul k din nodes
   u = 1; d = 1;
   for j = 1 : m
      if j ~= k
          u = u * (x - nodes(1, j)); % x - xj
          d = d * (nodes(1, k) - nodes(1, j)); % xk - xj
      end
   end
   
   val = val + f * (u / d);
end

end


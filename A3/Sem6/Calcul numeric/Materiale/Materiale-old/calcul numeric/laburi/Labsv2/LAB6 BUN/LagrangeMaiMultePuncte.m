function [vals] = LagrangeMaiMultePuncte(nodes, nodevals, points)
    vals = zeros(size(points));
    [~, col] = size(points);
    for i = 1 : col
       vals(1, i) = Lagrange(nodes, nodevals, points(1, i)); 
    end
end

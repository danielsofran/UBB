function [ val ] = LagrangeBari( nodes, nodevals, x )
    [~,col] = size(nodes);
    m = col;

    w = zeros(1, m);
    for j = 1 : m
        p = 1;
        for i = 1 : m
            if i ~= j
               p = p * (nodes(1, j) - nodes(1, i));
            end
        end
        w(1, j) = 1 / p;
    end

    u = 0; d = 0;
    for j = 1 : m
        u = u + (nodevals(1, j) * w(1, j)) / (x - nodes(1, j));
        d = d + w(1, j) / (x - nodes(1, j));
    end
    val = u / d;
end

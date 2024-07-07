function [val] = LagrangeV1(x, f, m, nodes)
    considered = nodes(1 : m);
    consideredVals = f;
    val = Lagrange(considered, consideredVals, x);
end
% problema 4
function [val] = LagrangeV2(x, f, m, nodes)
    considered = nodes(1 : m);
    consideredVals = f(considered);
    val = Lagrange(considered, consideredVals, x);
end

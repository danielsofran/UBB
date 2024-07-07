function sin_x = sin_approx(x)
    % Reducerea rangului: -?/2 ? x ? ?/2
    x = mod(x, 2 * pi);
    if x > pi
        x = x - 2 * pi;
    end

    % Calculul aproximativ al sinusului
    if abs(x) < 1e-8
        sin_x = x;
    elseif abs(x) > pi/6
        u = x / 3;
        sin_u = u * (-479249/(11511339840*u^6) + 34911/(7613320*u^4) - 29593/(207636*u^2) + 1/(1+1671/(69212*u^2) + 97/(351384*u^4) + 2623/(1644477120*u^6)));
        sin_x = (3 - 4 * sin_u^2) * sin_u;
    else
        u = x;
        sin_u = u * (-479249/(11511339840*u^6) + 34911/(7613320*u^4) - 29593/(207636*u^2) + 1/(1+1671/(69212*u^2) + 97/(351384*u^4) + 2623/(1644477120*u^6)));
        sin_x = sin_u;
    end
end

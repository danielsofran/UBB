function polinom = construiestePolinomHermite(Q_diagonala, noduri)
    syms x;
    % Q_diagonala - elementele de pe diagonala principala a matricei Q
    % noduri - nodurile duble

    n = length(Q_diagonala);
    polinom = 0; % Initializare polinom

    for j = 1:n
        termen = Q_diagonala(j);
        for i = 1:j-1
            termen = termen * (x - noduri(i));
        end
        polinom = polinom + termen;
    end
end
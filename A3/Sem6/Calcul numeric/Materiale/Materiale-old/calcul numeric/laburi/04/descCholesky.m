function R = descCholesky (A)

    %a=conjugata transpusei A
    if A == ctranspose(A)
   
    %det A >0
    %A - matrice patratica nxn
    n = length(A);
    R = A;
    for k = 1:n
        subA = A(1:k, 1:k); % Submatricea pãtratã de dimensiune k
        if det(subA) <= 0 % Verificarea valorii determinantului submatricei
            disp('Nu toti determinantii sunt pozitivi');
            return
        end
        disp('Toti determinantii sunt pozitivi');
    end
    for i = 1:n
        for j = i+1:n
            R(j,j:n) = R(j,j:n) - R(i,j:n)*R(i,j)' / R(i,i);
        end
        R(i,i:n) = R(i,i:n) / sqrt(R(i,i));
    end
    
    %matricea triughiulara superior
    R=triu(R);
    end
    
end

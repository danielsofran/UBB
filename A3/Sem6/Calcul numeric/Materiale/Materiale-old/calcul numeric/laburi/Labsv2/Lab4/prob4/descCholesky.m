function R = descCholesky (A)

    %A - matrice patratica nxn
    n = length(A);
    R = A;
    for i = 1:n
        for j = i+1:n
            R(j,j:n) = R(j,j:n) - R(i,j:n)*R(i,j)' / R(i,i);
        end
        R(i,i:n) = R(i,i:n) / sqrt(R(i,i));
    end
    
    %matricea triughiulara superior
    R=triu(R);
    
end

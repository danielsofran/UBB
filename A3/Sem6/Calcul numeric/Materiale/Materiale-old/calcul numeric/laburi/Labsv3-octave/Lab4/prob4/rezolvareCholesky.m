function x = rezolvareCholesky (Ar, b)

    x = Ar \ (Ar' \ b);

end

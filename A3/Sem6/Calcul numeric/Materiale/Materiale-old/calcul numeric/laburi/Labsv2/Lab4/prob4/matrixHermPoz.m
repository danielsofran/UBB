function A = matrixHermPoz (n)

  A = rand(n);
  A = A + A';
  A = A+max(sum(A,2))*eye(n);

endfunction

function [] = P4 ()

   %5.5737   1.0593   1.2728
   %1.0593   4.1293   1.3206
   %1.2728   1.3206   4.7684
   A = exMatrixHermitPoz ; 
   
   %1
   %2
   %3
   B = [1 ; 2 ; 3 ];
   
   disp("expected x:");
   fprintf("-0.0039873\n0.3114001\n0.5439645\n\n");
   
   Ar = descCholesky(A);
   x = rezolvareCholesky(Ar,B);
   x

end

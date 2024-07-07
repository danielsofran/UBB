 function [x,ni,rho]=Jacobi2(A,b,nr_max_it=1e+4,err=1e-14,p=Inf)
 %omega este omis pentru Jacobi si Gauss-Seidel
 #err e precizia, 14 zecimale exacte
 #ni numar iteratii
 M=diag(diag(A));
 N=M-A;
 T=M\N;
 c=M\b;
 rho=max(abs(eig(T))); %raza spectrala a lui T
 if norm(T,p)>=1
   disp('Abort!')
 endif
 factor=norm(T,p)/(1-norm(T,p));
 x_old=zeros(size(b)); ni=1;
 x=x_old;
 while ni<nr_max_it
   x=T*x_old+c;
   if factor*norm(x-x_old,p)<=err
     return;
   else
    x_old=x;
    ni=ni+1;
   endif
 endwhile
endfunction

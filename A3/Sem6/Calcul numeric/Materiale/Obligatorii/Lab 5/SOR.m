function [x,ni,rho,normT]=SOR(A,b,omega,err=1e-14,p=Inf)
M=1/omega*diag(diag(A))+tril(A,-1);
N=M-A;T=M\N;c=M\b;
rho=max(abs(eig(T))); 
 while norm(T,p)>=1
   fprintf('norm(T,p)=%f\n',norm(T,p));
   p=input("Dati alt p a.i. norm(T,p))<1:");
 endwhile
factor=norm(T,p)/(1-norm(T,p));
x_old=zeros(size(b)); ni=1;
x=x_old;
while ni<1e+4
   x=T*x_old+c;
   if norm(x-x_old,p)*factor<=err
     return;
   else  
    ni=ni+1;
    x_old=x;
   end 
end


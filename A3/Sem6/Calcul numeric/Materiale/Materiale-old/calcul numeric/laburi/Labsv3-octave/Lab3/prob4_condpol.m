function  p3_v1()
clf; hold on;
n=20;

a=poly(1:n);
 for i=1:100
   vect = randn(1,n+1) * 1e-10;
   ap = a+vect;
   r=roots(ap);
   plot(real(r),imag(r),'.k');
 
 end
 plot(1:n,zeros(1,n),'or');
 xlim([0 n+1]);
end
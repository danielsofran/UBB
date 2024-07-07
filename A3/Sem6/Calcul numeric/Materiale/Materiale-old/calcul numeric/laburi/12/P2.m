t=0:0.001:2*pi;
x=cos(t);y=sin(t);z=tan(t);
plot3(x,y,z,'r');
axis equal
%functia de gradul 3
x=-1:0.01:1;
y=x.^2;
z=x.^3
hold on
plot3(x,y,z,'k')


sist=@(x) [9*x(1).^2+36*x(2).^2+4*x(3).^2-36; x(1).^2-2*x(2).^2-20*x(3); x(1).^2-x(2).^2+x(3).^2];
derr=@(x,y,z) [18*x(1), 72*x(2), 8*x(3); 2*x(1), -4*x(2), -20; 2*x(1), -2*x(2), 2*x(3)];
X=[-1,-1,0; 1,-1,0; -1,1,0; 1,1,0]';

w=zeros(3,4);
ni=zeros(1,4);
k=0;

for x0=X
    k=k+1;
    [w(:,k),ni(:,k)] = Newton(sist,derr,x0,1e-6,0)
    plot3(w(1),w(2),w(3),'o')
end
disp(w);
disp(ni);
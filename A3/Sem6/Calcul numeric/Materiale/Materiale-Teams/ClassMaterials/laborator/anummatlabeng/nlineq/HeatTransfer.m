function [x,y]=HeatTransfer(L,c,n)

h = L/(n+1);
x =(0:n+1)*h;
f = @(u) c*(300^4 - u.^4);
df = @(u) -4*c*u.^3;
u0 = 900*ones(n,1);
[z,ni] = Newton(@F,@Jac,u0,1e-4,0,150);
y=[900;z;900];

    function y=F(u)
        ux=[900;u;900];
        y=h^2*f(u)+ux(1:end-2)-2*ux(2:end-1)+ux(3:end);
    end
    function y=Jac(u)
        y=diag(h^2*df(u)-2)+diag(ones(n-1,1),1)+diag(ones(n-1,1),-1);
    end
end

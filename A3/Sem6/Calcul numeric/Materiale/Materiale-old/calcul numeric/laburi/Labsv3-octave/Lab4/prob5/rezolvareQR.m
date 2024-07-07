function x = rezolvareQR (A, B)
  
    [L,U,P] = descLUP(A);
    pv = [];
    for i = 1:length(P)
        m = P(i,:);
        pv = [pv find(m==1)];
    end
    
    x = rezolvareLUP_p5(L,U,B,pv);
    
    %disp("Sistemul A*x=B ,unde :")
    %A
    %B
    %disp("are ca solutie:");
    %x
end

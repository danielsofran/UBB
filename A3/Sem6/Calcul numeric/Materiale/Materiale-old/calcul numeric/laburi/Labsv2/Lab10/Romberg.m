function rez = Romberg (f, a, b, ni, e)
  
    #ni - nr maxim iteratii
    R = zeros(ni,ni);
    R(1,1) = ((b-a)/2) * (f(a) + f(b));
    
    for i = 2:ni 
      s = 0;
      for j=1:power(2,i-2)
        h = (b-a) / power(2,i-2);
        s = s + f(a + (j-1/2) * h);
      endfor
      h = (b-a) / power(2,i-2);
      R(i,1) = (1/2) * (R(i-1,1) + h * s);
       
      for j=2:i
        p = 4^(j-1);  
        R(i,j) = (p*R(i,j-1)-R(i-1,j-1))/(p-1);
      endfor
      
      if abs(R(i-1,i-1) - R(i,i)) < e
        rez = R(i,i);
        return;
      endif
    endfor
    
    rez = "eroare";
    
endfunction

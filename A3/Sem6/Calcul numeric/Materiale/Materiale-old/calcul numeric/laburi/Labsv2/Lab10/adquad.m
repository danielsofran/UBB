function q = adquad (f,a,b,e)

  c = (a+b)/2;
  fa = f(a); 
  fb = f(b); 
  fc = f(c);
  q = quadstep(f,a,b,e,fa,fc,fb);
  
endfunction

function q = quadstep(f,a,b,e,fa,fc,fb)
  
  h = b-a;
  c = (a+b) / 2;
  fd = f((a+c)/2);
  fe = f((c+b)/2);
  
  q1 = (h/6) * (fa+4*fc+fb);
  q2 = (h/12) * (fa + 4*fb + 2* fc + 4*fe + fb);
  
  if abs(q1-q2) < e
    q = q2 + (q2-q1)/15;
    return;
  endif
  
  qa = quadstep(f, a, c, e, fa, fd, fc);
  qb = quadstep(f, c, b, e, fc, fe, fb);
  q = qa + qb;
  
endfunction
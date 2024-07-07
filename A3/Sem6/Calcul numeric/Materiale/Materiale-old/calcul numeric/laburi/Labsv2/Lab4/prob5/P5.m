function [] = P5 ()
  
  [A,B] = matrixP5(4);
  disp("sistemul A*x = B , unde :");
  A
  B
  
  x_qr = rezolvareQR(A,B);
  disp("are pentru descompunerea QR solutia:");
  x_qr
  
  x_lup = rezolvareLUP(A,B);
  disp("si pentru descompunerea LUP solutia:");
  x_lup

end

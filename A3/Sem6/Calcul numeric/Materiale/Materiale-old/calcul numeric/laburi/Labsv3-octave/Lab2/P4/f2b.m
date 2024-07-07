  
%#Conversia unui numar flotant in binar impartit pe cele 3 sectiuni:
%# - semn
%# - matisa
%# - exponent deplasat
function res = f2b(f)
  %#validarea datelor de input
  if ~isfloat(f)
    disp('Numar dat ca input trebuie sa fie FLOAT');
    return;
  end

  %#convertirea numarului flaot in hex
  h = num2hex(f); 
  
  array_h = [];
  for i=1:length(h)
    %#convertirea din hexa in bin
    d = hex2dec(h(i));
    b = dec2bin(d);
    array_h=[array_h repmat('0', 1, 4-length(b)) b];
    %#array_h = [ array_h; b];
  end
  
  if isa(f, 'single')
        res=['semn ' array_h(1) ' | mantisa ' array_h(2:9) ' | exponent deplasat ' array_h(10:end)];
  else
        res=['semn ' array_h(1) ' | mantisa ' array_h(2:12) ' | exponent deplasat ' array_h(13:end)];
  end
  
  
end
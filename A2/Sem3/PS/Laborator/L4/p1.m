pkg load statistics

# HAM FREQ
ham=fileread('keywords_ham.txt'); # continutul
hWords=strsplit(ham, ' '); # vector de cv
uhWords=unique(hWords); # doar cv unice
lh=length(uhWords);
uhWords=uhWords(2:lh); # ia primul cv spatiu, sau un substr

hFreq = [];
uhL=length(uhWords);
hL=length(hWords);

for i=1:uhL
  counter=0;
  for j=1:hL
    counter+=strcmp(uhWords(i), hWords(j));
  endfor
  hFreq = [hFreq, counter / hL];
  # probabilitatea care ne trebe P(Wi=true|C=ham)
  # numarul de aparitii ale cuvantului call ın keywords ham.txt / numarul de cuvinte din keywords ham.txt
endfor

# SPAM FREQ
spam=fileread('keywords_spam.txt'); # continutul
sWords=strsplit(spam, ' '); # vector de cv
usWords=unique(sWords); # doar cv unice
ls=length(usWords);
usWords=usWords(2:ls); # ia primul cv spatiu, sau un substr

sFreq = [];
usL=length(usWords);
sL=length(sWords);

for i=1:usL
  counter=0;
  for j=1:sL
    counter+=strcmp(usWords(i), sWords(j));
  endfor
  sFreq = [sFreq, counter / sL];
  # probabilitatea care ne trebe P(Wi=true|C=spam)
  # numarul de aparitii ale cuvantului call ın keywords spam.txt / numarul de cuvinte din keywords spam.txt
endfor

# EMAIL
email=fileread('email1.txt');
eWords=strsplit(email, ' ');
ueWords=unique(eWords);
eL=length(eWords);

for i=1:uhL
  counter=0;
  for j=1:eL
    counter+=strcmp(uhWords(i), eWords(j));
  endfor
  if counter == 0
    hFreq(i)=1-hFreq(i);
  endif
endfor

for i=1:usL
  counter=0;
  for j=1:eL
    counter+=strcmp(usWords(i), eWords(j));
  endfor
  if counter == 0
    sFreq(i)=1-sFreq(i);
  endif
endfor

# inmultim probabilitatile
PH=length(hWords)/(length(hWords)+length(sWords));
PS=length(sWords)/(length(hWords)+length(sWords));
PW1_14_ham=prod(hFreq)*PH
PW1_14_spam=prod(sFreq)*PS

if PW1_14_spam > PW1_14_ham
  printf("Email is spam!\n")
else
  printf("Email is ham!\n")
endif






G1. grep --color -E '^(...)*$' fisier
g2. grep --color -E '[AEIUO]{1}.*[AEIOU]{1}.*[AEIOU]{1}' fisier | grep -v -E '[13579]{1}'
g3. cat fisier | grep --color -E '^([13579][13579]){0,}([^02468]{0,})([02468][02468]{0,}[02468]$)'
g4. cat fisier | grep --color -v -E '[13579]+' | grep --color -E     '^[^AEIOU]*([AEIOU]{1}[^AEIOU]*){,3}$'
g5. 
g6. cat fisier | grep -c -E '^[A-Z]+$'
g7. cat fisier | grep -n -E '^[0-9]{2}.*[0-9]{2}$'
g8. cat fisier | grep --color -E -o '\$[^\$]*$'
g9. cat fisier | grep -v -E '[a-zA-Z0-9]+'

s1. cat fisier | sed -E 's/a/A/g'| sed -E 's/e/E/g'| sed -E 's/i/I/g' | sed -E 's/o/O/g'| sed -E 's/u/U/g'
s1. cat fisier | sed -E 's/([aeiou])/\U\1/g'
s2.  cat fisier | sed -E -e 's/^..//' -e 's/...$//'
s3. cat fisier | sed -E 's/([A-Z])/\1\1\1/g'
s4. cat fisier | sed -E 's/([0-9]{2,})/\{\1\}/g'
s5. cat fisier | sed -E 's/ [^ ]+//'
s6. ?sed -E 's/^([^ ]+ )(([^ ]*[ ]{0,})*)( [^ ]+$)/\4 \3 \1/'
s6. sed -E 's/^([^ ]+) (.*) ([^ ]+)$/\3 \2 \1/'
s7 cu cuvinte. sed -E 's/(([ ]*)([^ ])([^ ]*)([^ ])([ ]*))/\2\5\4\3\6/g'
s7. sed -E 's/^(.)(.*)(.)$/\3\2\1/'
s8. sed -E 's/[AEIOU]{3,}//g'


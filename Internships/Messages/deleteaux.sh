#!/bin/bash

NR=0

echo "Deleting..."

for FILE in `find . -name "coverletter_*.txt"`
do
	rm "$FILE"
	echo "$FILE"
	NR=$((NR+1))
done


for FILE in `find . -name "hrmessage_*.txt"`
do
	rm "$FILE"
	echo "$FILE"
	NR=$((NR+1))
done

echo "$NR files deleted"

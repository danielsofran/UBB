#!/bin/bash

if [ $# -ne 1 ]; then
	echo "Introduceti numele firmei ca param!" >&2
	return 1
fi

FIRMA=$1

FILE="hrmessage.txt"
FILE_OUT="hrmessage_$FIRMA.txt"

sed "s/__FIRMA__/$FIRMA/g" "$FILE" > "$FILE_OUT"
cat "$FILE_OUT" > /dev/clipboard

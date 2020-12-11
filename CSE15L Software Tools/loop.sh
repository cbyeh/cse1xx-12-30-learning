#!/bin/bash

i=1
sum=0

while [ "$i" -le 10 ]; do
  echo "Adding $i into the sum."
  sum=`expr $sum + $i`
  i=$((i + 1))
done

echo "The sum is $sum"

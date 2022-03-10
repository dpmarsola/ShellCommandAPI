#!/usr/bin/bash

result=$(cat /home/daniel/workspace/temp/grepresult)

IFS=""

for i in $result
do
    echo $i
done 

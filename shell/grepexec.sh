#!/usr/bin/bash

pathDIR="/home/daniel/workspace/temp"
flagReadNext=0       # flag that says if you are reading a KEY or a VALUE. Whenever reading a VALUE, you just move to the next argument which will be a KEY.
max=0
declare -a fmtCmd    # this array will hold all the grep commands, each element of the array is 1 filter to be used in the grep command. 

rm $pathDIR/tmp.cmdshell.* $pathDIR/grepresult* 2> /dev/null # clean files that eventually were left over from previous processings.
cp $pathDIR/fluxo.debug $pathDIR/tmp.cmdshell.0

for i in $@
do
    idx=${#fmtCmd[@]}

    if [ $flagReadNext == 1 ]  # in case this is a VALUE and not a KEY, we will just move to the next element. VALUES will be accessed by the shift command.
    then
        flagReadNext=0
        shift 1
    else
        flagReadNext=1      # being here, means that this is a KEY. Shifting the element will access the VALUE.
        case $i in
        "data"|"hora"|"parceiro")    
            shift 1 
            fmtCmd[$idx]="$1"   # store in an array each VALUE you want to use as filter for the command.
            ;;
        esac
    fi
done

# the tmp files will be used to filter things out between the iterations.
for i in ${!fmtCmd[@]}  
do

    last=$i                  # this will reference the file that was create before this iteration
    next=$(expr $i + 1)      # this will reference the file that will be created during this iteration
    max=$next                # whenever the loop stops this will indicate the most filtered tmp file

    grep ${fmtCmd[$i]} < <(cat $pathDIR/tmp.cmdshell.$last) > $pathDIR/tmp.cmdshell.$next  # because the pipe does not work with string substitution, we will use this method
                                                                         # where we read from the last tmp file, filter it using grep, and create a new 
                                                                         # filtered tmp file that will be read in the next iteration
    
done

cat $pathDIR/tmp.cmdshell.$max > $pathDIR/grepresult

rm $pathDIR/tmp.cmdshell.* 2> /dev/null

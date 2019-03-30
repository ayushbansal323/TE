%macro print 2
mov rax,01
mov rdi,0
mov rsi,%1
mov rdx,%2
syscall
%endmacro
%macro read 2
mov rax,0
mov rdi,1
mov rsi,%1
mov rdx,%2
syscall
%endmacro
;++++++++++++++++++++++++++++++++++++++

section .data
msg1 : db 10,"The mean is ",
len1 :equ $-msg1
msg2: db 10,"The variance is ",
len2 :equ $-msg2
msg3: db 10,"The standard deviance is ",
len3: equ $-msg3
data: dd 2.0,20.0,30.0
dcpt: db "."
precision dw 10000 ;used for making the precision 4 points after decimal
section .bss

count2 resw 1  ;additional counter
count resw 1  ;counter
h2acount resb 1
bcd rest 1  ;to store bcd obtained for the printing of results
mean resd 1  ;required for calculating the variance

dbuff resw 1  ;used for storing byte in ascii
section .text

global _start:
_start:

finit   ;initialize the math-coprocessor
fldz   ;push 0.0 onto fpu stack
mov r8,data  ;set r8 to point the begining of the data array
mov r9,0
mov word[count],3h ;initialize the counter
addloop:
fadd dword[r8]
add r8,4
dec word[count]
jnz addloop

mov word[count],3
   ;the sum is now at stack 0
fidiv word[count]
fst dword[mean]  ;mean is now obtained and stored

print msg1,len1
call s0printer
   ;mean is now printed proceding for variance
   ;load st0 with 0.0
mov r8,data
mov word[count2],3
fldz   ;stack initialization remove this line and see 
fldz   ;what happens spent hours debugging this
varloop:
fldz   ;cumulative answer at st1
fld dword[r8]  ;cumulative answer at st2
fsub dword[mean] ;st0=x-mean,st1=0;st2=cumulative answer here
fst st1   ;st1=st0
fmulp st1,st0  ;st0=(x-mean)^2,st1=cumulative answer
fadd   ;st0=cumulative answer

add r8,4
dec word[count2]
jnz varloop

mov word[count2],3
fidiv word[count2]
fst st1   ;storing variance value here for std deviation
print msg2,len2
call s0printer

fsqrt   ;using the earliear stored value
print msg3,len3
call s0printer
 
exiter:
mov rax,60
mov rdi,0h
syscall

s0printer:
fimul word[precision]
fbstp tword[bcd] ;store bcd integer and pop
mov word[count],8h
mov r8,bcd
add r8,9  ;go to last byte of answer
printloop:
mov al,byte[r8]
call h2a
print dbuff,2
dec r8
dec word[count]
jnz printloop
print dcpt,1  ;print the decimal point now
mov byte[count],2
printloop2:
mov al,byte[r8]
call h2a
print dbuff,2
dec r8
dec word[count]
jnz printloop2
ret

h2a:   ;hex to ascii,value at al, output at display buff
mov r11,dbuff  ;used to point at the base of dbuff
mov byte[h2acount],2
mov bl,0h
h2aloop:
rol al,4
mov bl,al
and bl,0Fh
cmp bl,9h
jbe nocorrection
add bl,7h
nocorrection:
add bl,30h
mov byte[r11],bl
inc r11
dec byte[h2acount]
jnz h2aloop

ret

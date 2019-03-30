;working
.model small

.stack 100

.data
msg db 10,'this is cos wave$'
x dd 0.0
xad dd 3.0
one80 dd 180.0
sixty dd 30.0
hundred dd 50.0
xcursor dd 300
ycursor dt 500
count dw 360
x1 dw 0


.code
.386
main:
mov ax,@data;to intialize the DS data segment
mov ds,ax;since we cannot intialize ds directly we use AX register                                                                                                                                                                                                                                                                                                                                                                       
mov ax,6             ;set video mode in grayscale and 13h for VGA
int 10h;to call BIOS interrupt

up1:finit

fldpi;load value of pi and puts it on Top of stack
fdiv one80;it divides value on top of stack by 
; one180 and again it put it on the stack
fmul x;it mul
fcos
fmul sixty
fld hundred
fsub st,st(1)   ;=100-60 sin((pi/180))*x)


fbstp ycursor
lea esi,ycursor

mov ah,0ch              ;write graphics pixel
mov al,01h
mov bh,0h
mov cx,x1
mov dx,[si];we are putting the starting coordinate of display  
int 10h

inc x1                 
fld x                  
fadd xad               
fst x                  
dec count              
jnz up1   ;for plotting the wave from 0 to 2pi             


mov ah,09h                      ; display message
lea dx,msg
int 21h;to call interrupt for dos display


mov ah,4ch
int 21h
end main


section .data		
	nline	db 	10,10
	nline_len  equ   $-nline
	
	ano     db       10,"ASSIGNMENT NO.:3",
	db  10,"---------------------------------------------------------------",
	
	db    10,"Assignment name : Conversion from HEX to BCD and BCD  to HEX Number.",
	db   10,"-----------------------------------------------------------",10
	
	ano_len  	equ	$-ano
		
	
	menu		db  10,"1.Hex To BCD.",
			db  10,"2.BCD to Hex.",
			db  10,"3.Exit."
			db  10,"Enter your choice:"
	menu_len 	equ  $-menu
	
	hmsg	 	db 	 10,"Enter 4  digit HEX Number.:"
	hmsg_len	equ  	$-hmsg
	
	bmsg		db	10,"Enter 5  digit BCD Number.:"
	bmsg_len 	equ	$-bmsg
		
	ebmsg		db 	10,"The Equivalent BCD Number.:"
	ebmsg_len	equ	$-ebmsg
		
	ehmsg   	db	10,"the Equivalent Hex  Number.:"
	ehmsg_len 	equ 	$-ehmsg
		
	emsg		db	10,"INVALID NUMBER INPUT",10
	emsg_len   	equ	$-emsg
	
section .bss
	buf		resB  	6
	char_ans	resB	4
	ans		resW	1
	
 %macro		Print 2
	mov 	rax,1
	mov	rdi,1
	mov 	rsi,%1
	mov	rdx,%2
      syscall
%endmacro


%macro  	Read 2
 	mov	rax,0
 	mov	rdi,0
 	mov	rsi,%1
 	mov	rdx,%2
       syscall
%endmacro

%macro  Exit  0
  	Print nline,nline_len
  	mov 	rax,60
  	mov	rdi,0
      syscall
%endmacro


section .text
	global _start

_start:
		Print ano,ano_len
Menu:Print menu,menu_len
	Read 	buf,2		;accept choice i.e 1 digit+enter
	mov	al,[buf]	;contains only digit character

c1:	cmp  	al,'1'
	jne	c2
	call	HEX_BCD
	jmp	Menu
	
c2:	cmp    al,'2'
 	jne    c3
 	call   BCD_HEX
 	jmp    Menu
 	
c3:	cmp	al,'3'
 	jne	invalid
  	Exit
 	
 	
invalid:
	Print emsg,emsg_len
	jmp	menu
	
	
HEX_BCD:
	Print  hmsg,hmsg_len
	call	Accept_16	;accept 4 digit hex number
	mov	ax,bx		;mov hex number in ax
	
	mov	bx,10		;for divide hex number by 10
	xor	bp,bp		;counter
back:xor	dx,dx		;as dx each time contains remainder
	div	bx		;divide ax by 10 ax=Q dx=R
	push	dx		;push dx on stack as it is bcd
	inc	bp		;inc coun	ter by	 1
	
	cmp	ax,0		;compare whether Q is 0 if 0 means number get over 
	jne	back
	
	
	Print ebmsg,ebmsg_len
back1:  pop	dx	;pop last digit pushed on stack
	add	dl,30h	;add 30 to digit to make them decimal
	mov 	[char_ans],dl		;print individual digit
	Print  char_ans,1
	
	dec	bp
	jne	back1		;mov to next digit
	
RET


BCD_HEX:
	Print  bmsg,bmsg_len
	Read   buf,6		;5 digit + 1 enter
	
	mov	rsi,buf	;pointer at the start of buffer
	xor	ax,ax	;previous digit=0
	mov	rbp,5	;counter
	mov	rbx,10	;multiplier
	
next:xor 	cx,cx		;contains next digit each time
	mul 	bx		;(ax*bx)+cl
	mov	cl,[rsi]
	sub	cl,30h
	add	ax,cx
	
	inc 	rsi		;point to next digit
	dec 	rbp
	jne	next
	
	mov	[ans],ax 	;store ax in ans because ax get change in print macro
	Print ehmsg,ehmsg_len
	
	mov	ax,[ans]
	call 	Disp_16		;Print  hex number
	
RET

Disp_16:				;Hex to Ascii(character) display
	mov	rsi,char_ans+3	
	mov	rcx,4			;counter	
	mov	rbx,16			;Hex no
	
next_digit:
 	xor	rdx,rdx
 	div 	rbx
 	cmp	dl,9
 	jbe	add30
 	add	dl,07h
 	
 add30:
 	add 	dl,30h
 	mov 	[rsi],dl
 	
 	dec 	rsi
 	dec	rcx
 	jnz	next_digit
 	
 	Print char_ans,4
 ret
 
Accept_16:
	Read  	buf,5
	mov	rcx,4
	mov 	rsi,buf
	xor	bx,bx
	
next_byte:
  	shl	bx,4
  	mov 	al,[rsi]	 	
 	cmp	al,'0'
 	jb	error
 	cmp	al,'9'
 	jbe	sub30
 	
 	cmp	al,'A'
 	jb	error
 	cmp 	al,'F'
 	jbe	sub37
 	
 	cmp	al,'a';/
 		
 	cmp 	al,'f'
 	jbe	sub57

error:
 Print	 emsg,emsg_len
 	
sub57: 	SUB	al,20h
sub37:	SUB	al,07h
sub30:	SUB 	al,30h

 	add	bx,ax
 	
 	inc	rsi
 	
 	dec	rcx
 	jnz 	next_byte
 
RET











	 	 	
 	
 		
	
						      	        	
						

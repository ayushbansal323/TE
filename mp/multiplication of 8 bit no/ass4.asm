section .data
	nline	db	10,10
	nline_len	equ	$-nline
	ano	db	10,"Assignment no.:4",
		db	10,"---------------------------------------------------------------",
		db	10,"Assignment Name : Multiplication of 16 bit number.",
		db 	10,"  (using successive Addition and Shift and Add method)",
		db	10,"----------------------------------------------------------------",
	ano_len 	equ	$-ano
	menu	db	10,"1.Multiplilcation By Successive Addition.",
	 	db	10,"2.Multiplication by shift and add method.",
	 	db	10,"3.Exit."
	 	db 	10,"Enter your choice::"
	 	
	 menu_len	equ	$-menu
	 n1msg		db	10,"Enter first no.(xxxx)::"
	 n1msg_len	equ	$-n1msg
	 
	 n2msg		db	10,"Enter second no.(xxxx)::"
	 n2msg_len	equ	$-n2msg
	 
	 samsg		db	10,"Multiplication By Successive Addition::"
	 samsg_len	equ	$-samsg
	 
	 shmsg		db	10,"Multiplication By shift and add method::"
	 shmsg_len	equ	$-shmsg
	 
	 emsg		db	10,"Invalid number input",10
	 emsg_len	equ	$-emsg
	 
	 
section	.bss
 	buf 	resB	5
 	buf_len 	equ 	$-buf
 	
 	n1	resW	1
 	n2 	resW	1
 	
 	ans1 	resW	1
 	ansh	resW	1
 	
 	ans	resD	1
 	
 	char_ans	resB	4
 	
 	
 	
 %macro		print	2
  	mov	rax,1
  	mov	rdi,1
  	mov	rsi,%1
  	mov	rdx,%2
     syscall
 %endmacro
 
 %macro		read	2
  	mov	rax,0
  	mov	rdi,0
  	mov	rsi,%1
  	mov	rdx,%2
     syscall
 %endmacro
 
 %macro		Exit 	0
  	print nline,nline_len
  	mov	rax,60
  	mov	rdi,0
     syscall
 %endmacro
 
 
 section  .text
 	global _start
 	
 _start:
 	   print ano,ano_len
 	   
 Menu: print	menu,menu_len
 	read  buf,buf_len
 	mov	al,[buf]	
 	
 c1:	cmp	al,'1'
 	jne	c2
 	call	sa
 	jmp	Menu
 	
 c2:	cmp	al,'2'
 	jne	c3
 	call	ad_sh
 	jmp	Menu
 	
 c3:	cmp	al,'3'
 	jne	invalid
 	Exit
 	
 invalid:print  emsg,emsg_len
 	jmp	Menu
 	
 	
 sa:
 	mov	word[ansh],00	;for next time use
 	mov 	word[ans1],00
 	
 	print n1msg,n1msg_len
 	call 	Accept_16
 	mov	[n1],bx
 	
 	print n2msg,n2msg_len
 	call	Accept_16
 	mov	[n2],bx
 	
 	mov	ax,[n1]
 	mov	cx,[n2]
 	
 	cmp	cx,0	;if cx is 0 then multipli. is 0
 	je	final
 	
 back:add	[ans1],ax	;add n1 to ans1
 	jnc	next		;if not carry
 	inc	word[ansh]
 	
 next:dec	cx
 	jnz	back		;jmp for next time addition
 	
 final:
       print		samsg,samsg_len
       mov	ax,[ansh]
       call 	disp_16
       
       mov	ax,[ans1]
       call	disp_16
       
  ret
  
  ad_sh:
  	mov	dword[ans],00	;dword bcz multiplication of 2 16 bit no may be 32 bit
  	print n1msg,n1msg_len
  	call  	Accept_16
  	mov	[n1],bx
  	
  	print n2msg,n2msg_len
  	call	Accept_16
  	mov	[n2],bx
  	
  	xor	rax,rax
  	
  	xor	rbx,rbx
  	
  	mov 	ax,[n1]
  	mov	bx,[n2]
  	mov	cx,16
  	
  	mov	ebp,0
  	
  back1:
   	shl	ebp,1
   	shl	ax,1
   	jnc	next1
   	add	ebp,ebx
   	
   next1:
        loop  back1
        
        mov  [ans],ebp
        print shmsg,shmsg_len
        mov	eax,[ans]
        call 	disp_32
        
   ret
   
   disp_16:
   	mov	rsi,char_ans+3
   	mov	rcx,4
   	mov	rbx,16
   	
   next_digit:
   	xor	rdx,rdx
   	div	rbx
   	
   	cmp	dl,9
   	jbe	add30
   	add	dl,07h
   	
   add30:
   	add	dl,30h
   	mov	[rsi],dl
   	
   	dec	rsi
   	dec	rcx
   	jnz	next_digit
   	
   	print char_ans,4
   	
   ret
   
   disp_32:
    	mov	rsi,char_ans+7
    	mov	rcx,8
    	mov	rbx,16
    	
    next_digit1:
    	xor	rdx,rdx
    	div	rbx
    	
    	cmp	dl,9
    	jbe	add301
    	add 	dl,07h
    	
    add301	:
    	add		dl,30h
    	mov	[rsi],dl
    	
    	dec	rsi
    	dec	rcx
    	jnz	next_digit1
    	
    	print char_ans,8
    	
    ret
    
   Accept_16:
   	read  buf,buf_len
   	
   	mov	rcx,4
   	mov	rsi,buf
   	xor	bx,bx
   	
   next_byte:
   	shl	bx,4
   	mov	al,[rsi]
   	
   	cmp	al,'0'
   	jb	error
   	cmp	al,'9'
   	jbe	sub30
   	cmp	al,'a'	
   	jb	error
   	cmp	al,'f'
   	jbe	sub37
   	
   	cmp	al,'a'
   	jb	error
   	cmp	al,'f'
   	jbe	sub57
   	
  error:
   	print		emsg,emsg_len
   	jmp	Menu
   	
   	
   sub57: 	sub	al,20h
   sub37:	sub	al,07h
   sub30:	sub	al,30h
     	
     		add 	bx,ax
     		
     		inc	rsi
     		dec	rcx
     		jnz	next_byte
     		
     ret			 	

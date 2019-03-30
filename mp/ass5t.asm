section .data
	nline db	10,10
	nline_len	equ 	$-nline
	colon	db	":" 
	rmsg  db   10,"Processor is in Real mode...",0
	rmsg_len   equ	$-rmsg
	pmsg  db   10,"Processor is in Proctected Mode...",0
	pmsg_len   equ	$-pmsg
	
	gmsg  db   10,"GDTR :",0
	gmsg_len   equ 	$-gmsg
	
	imsg  db   10,"IDTR :",0
	imsg_len   equ 	$-imsg
	
	mmsg  db   10,"MSW(Machine status word):",0
	mmsg_len   equ	$-mmsg
;--------------------------------------------------------	
section .bss
  	GDTR	resw 3
  	IDTR	resw 3
  	MSW	resw 1
  	
  	char_ans	resb 16
;--------------------------------------------------------
%macro	Print	2
	mov 	rax, 1
	mov	rdi, 1
	mov	rsi, %1
	mov	rdx, %2
	syscall
%endmacro

%macro	Exit	0
        mov 	rax, 60
	mov	rdi, 0
	
	syscall
%endmacro
;------------------------------------------------------
section .text
	global  _start
_start:

	SMSW 	[MSW]	
	
	mov 	rax,[MSW]
	ror	rax,1   ;ror destination, count
	jc	p_mode  ; if carry is generated then mode is protected mode
	
	Print rmsg,rmsg_len
	jmp	next
	
p_mode:
	Print pmsg,pmsg_len
	
next:
	SGDT  [GDTR]
	SIDT  [IDTR]
	SMSW  [MSW]
	
	Print  gmsg,gmsg_len
	mov	rax,0
	;mov	rax,[GDTR+4] ;1st to 4th
	;call    disp16_proc
	;mov	ax,[GDTR+2] ;5th to 8th
	;call	disp16_proc
	;Print	colon,1
	mov	rax,[GDTR] ;;limit
	call	disp16_proc
	
	Print imsg,imsg_len
	;mov	ax,[IDTR+4]
	;call	disp16_proc
	;mov	ax,[IDTR+2]
	;call	disp16_proc
	
	;Print	colon,1
	mov	rax,[IDTR]
	call	disp16_proc
	
	Print 	mmsg,mmsg_len
	mov	ax,[MSW]
	call 	disp16_1proc
	
	Print nline,nline_len
	Exit
;--------------------------------------------------
disp16_proc:  ;number the ascii
	mov	rbx,16
	mov	rcx,12
	mov	rsi,char_ans+11
cnt:	mov 	rdx,0
	div	rbx
	cmp	dl, 09h ;rdx=remainder ; rax=Quotient 
	jbe	add30
	add	dl, 07h
add30:
	add	dl,30h
	mov	[rsi],dl
	dec	rsi
	dec	rcx
	jnz	cnt
	Print	char_ans,12
ret	


;----------------------------------------------------------
disp16_1proc:
	mov	rbx,16
	mov	rcx,4
	mov	rsi,char_ans+3
cnt1:	mov 	rdx,0
	div	rbx
	cmp	dl, 09h
	jbe	add301
	add	dl, 07h
add301:
	add	dl,30h
	mov	[rsi],dl
	dec	rsi
	dec	rcx
	jnz	cnt1
	Print	char_ans,4
ret	
        

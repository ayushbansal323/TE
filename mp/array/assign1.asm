;;%include "macro.asm"
SECTION .data
	nline db 10,10
	nline_len equ $-nline

	ano	db 	10,"  Assignment No. :1",10
	   	db	"Positive / Negative elments form 64-bit array",10
	ano_len equ	$-ano

	arr64	dq  111111, 222222, 133333333, 44444444, -55555555
	
	n	equ	5

	pmsg	db 10,10,"The no. of Positive elements from 64-bit array:"

	pmsg_len equ $-pmsg
	
	nmsg	db 10,10,"The no. of Negative elements from 64-bit array:"
 
	nmsg_len equ $-nmsg




SECTION .bss ;block started by segment 
	p_count		resq	 1
	n_count		resq	 1

	char_ans	resb	 16  ;;?

%macro Print 4
	mov	rax, %1
	mov	rdi, %2
	mov	rsi, %3
	mov	rdx, %4
	syscall
%endmacro
;
;%macro Read 2
;	mov	rax, 0
;	mov	rdi, 0
;	mov	rsi, %1
;	mov	rdx, %2
;	syscall
;%endmacro

%macro	exit	0
	mov	rax, 60
	mov	rdi, 0
        syscall
%endmacro




SECTION .text
	global _start
_start:
	Print	1,1,ano, ano_len
	mov	rsi, arr64
	mov	rcx, n
	mov	rbx, 0; +ve
	mov	rdx, 0; counter for negative 

next_num:
	mov	rax,[rsi]
	shl	rax,1      ;shift logical left
	jc	negative

positive:
	inc	rbx
	jmp	next

negative:
	inc	rdx
next:
	add 	rsi, 8 ;;8 byte =64 bit 
	dec	rcx
	jnz	next_num
	mov	[p_count], rbx ;[p_count]
	mov	[n_count], rdx
	Print	1,1,pmsg, pmsg_len
	mov	rax,[p_count]
	call disp8_proc
	Print	1,1,nmsg, nmsg_len
	mov	rax,[n_count]
	call disp8_proc
	Print   1,1,nline, nline_len
	exit
disp8_proc:

	mov	rsi, char_ans
	mov	rcx, 1    ;;?
	mov	rbx, 16

next_digit:
	mov	rdx, 0
	div	rbx
	cmp	dl, 09h  ;dl d_lower && dh d_higher
	jbe	add30
	add	dl, 07h
add30:
	add	dl,30h
	mov	[rsi],dl
	dec	rsi
	dec	rcx
	jnz	next_digit

	Print 1,1,char_ans, 1
ret
	






























	


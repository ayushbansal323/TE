section .data

msg dw 10,"this is pratice program",10,0
msg_len equ $-msg

nline dq 10


number equ 88

section .bss

charin resb 2

charans resb 16

section .text
%macro printf 2
mov rax,1
mov rdi,1
mov rsi,%1
mov rdx,%2
syscall
%endmacro

%macro scanf 2
mov rax,0
mov rdi,0
mov rsi,%1
mov rdx,%2
syscall
%endmacro

%macro exit 0
mov rax,60
mov rdi,0
syscall
%endmacro
	global _start
_start:
	printf msg,msg_len
	scanf charin,2
	printf charin,2
	printf nline,1
	mov rax,255
	call display_hex
	printf charans,6
	printf nline,1
	mov rax,0ffh
	call display_dec
	printf charans,6
	printf nline,1
	exit
	
display_hex:
	mov rsi,charans+5
	mov rcx,6
	mov rbx,16
	
hexloop:
	mov rdx,0
	div rbx
	cmp dl,9
	jbe add48
	add dl,7
add48:
	add dl,48
	mov [rsi],dl
	dec rsi
	dec rcx
	cmp rcx,0
	jnz hexloop
	ret

display_dec:
	mov rsi,charans+5
	mov rcx,6
	
	
decloop:
	mov rbx,10
	mov rdx,0
	div rbx
	add dl,48
	mov [rsi],dl
	dec rsi
	dec rcx
	cmp rcx,0
	jnz decloop
	ret
	

section .data

msg dw 10,"this is pratice program",10,0
msg_len equ $-msg

nline dq 10


number equ 88

section .bss

charin resb 3

charans resb 16

num1 resq 1

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
	scanf charin,3
	printf charin,2
	printf nline,1
	call ascii_dec

	call display_dec
	printf charans,6
	
	
	printf nline,1
	scanf charin,3
	printf charin,2
	printf nline,1
	call ascii_hex
	
	call display_hex
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
	
ascii_dec:
	mov rsi,charin
	mov rcx,2
	
	mov rax,0

loopdec:
	mov rbx,10
	mul rbx
	mov rdx,0
	mov dl,[rsi]
	cmp dl,'0'
	jb loops
	cmp dl,'9'
	jg loops
	sub dl,48
	add al,dl
loops:
	inc rsi
	dec rcx
	cmp rcx,0
	jnz loopdec
	ret
	
ascii_hex:
	mov rsi,charin
	mov rcx,2
	
	mov rax,0

loophex:
	mov rbx,16
	mul rbx
	mov rdx,0
	mov dl,[rsi]
	cmp dl,'0'
	jb loopshex
	cmp dl,'9'
	jbe sub48
	cmp dl,'A'
	jb loopshex
	cmp dl,'F'
	jbe sub55
sub55:
	sub dl,7
sub48:
	sub dl,48
	add al,dl
loopshex:
	inc rsi
	dec rcx
	cmp rcx,0
	jnz loophex
	ret
	
	

section .data

msg db "this is pratice program"
msg_len equ $-msg

file dw 10,"file close",10,0
file_len equ $-file

rels dw 10,"this is protected",10,0
rels_len equ $-rels

nline dq 10



number equ 88

section .bss

filename resb 50

charin resb 1024

cmpchar resb 2

charans resb 16
charans1 resb 16

abuf_len resq 1

FD resq 1

ncnt resq 1
scnt resq 1
ccnt resq 1

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

%macro fopen 1
mov rax,2
mov rdi,%1
mov rsi,2 ;wr
mov rdx,0777
syscall 
%endmacro

%macro fclose 1
mov rax,3
mov rdi,%1
syscall
printf file,file_len
%endmacro

%macro fwrite 3
mov rax,1
mov rdi,%1
mov rsi,%2
mov rdx,%3
syscall
%endmacro

%macro fread 3
mov rax,0
mov rdi,%1
mov rsi,%2
mov rdx,%3
syscall
%endmacro

%macro lseek 1
mov rax,8
mov rdi,%1
mov rsi,0
mov rdx,0
syscall
%endmacro
	global _start
_start:
	scanf filename,50
	dec rax
	mov byte[filename + rax],0
	mov rax,0
	fopen filename
	cmp rax,-1H
	jle exitss
	mov [FD],rax
	call display_dec
	printf charans,6
	printf nline,1 
	
	fwrite [FD],msg,msg_len
	
	lseek [FD]
	
	fread [FD],charin,1024
	mov [abuf_len],rax
	printf charin,[abuf_len]
	
	scanf cmpchar,2
	
	mov rsi,charin
	mov rcx,[abuf_len]
	mov rbx,0

lop:
	mov al,[rsi]	
	cmp al,10
	jne nxt1
	inc qword[ncnt]
nxt1:
	cmp al,32
	jne nxt2
	inc qword[scnt]
nxt2:
	cmp al,[cmpchar]
	jne nxt3
	inc qword[ccnt]		
nxt3:	
	inc rsi
	dec rcx	
	cmp rcx,0
	jnz lop
	
	
	mov rax,[ncnt]
	
	call display_dec
	printf charans,6
	printf nline,1
	
	mov rax,[scnt]
	
	call display_dec
	printf charans,6
	printf nline,1
	
	mov rax,[ccnt]
	
	call display_dec
	printf charans,6
	printf nline,1

	mov rax,0
	fclose [FD]
	call display_dec
	printf charans,6
exitss: 
        
	exit
	
display_hex:
	mov rsi,charans+11
	mov rcx,12
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
	
display_hexmsw:
	mov rsi,charans1+3
	mov rcx,4
	mov rbx,16
	
hexloopmsw:
	mov rdx,0
	div rbx
	cmp dl,9
	jbe add48msw
	add dl,7
add48msw:
	add dl,48
	mov [rsi],dl
	dec rsi
	dec rcx
	cmp rcx,0
	jnz hexloopmsw
	ret	

section .data
      nline       db   10,10
      nline_len   equ   $-nline

      msg         db      10,"MIL assignment 02: Overlapped Block  Transfer"
      db  10,"-----------------------------------------------------"

      db   10,"      (Without string instructions)      "
      msg_len    equ   $-msg

      bfrmsg     db    10,10,"1)Block Contents Before Transfer",10
      bfrmsg_len    equ   $-bfrmsg
     
      afrmsg  db    10,10,"2)Block  contents After  Transfer",10
      afrmsg_len    equ  $-afrmsg
    
      srcmsg     db   10,"Source block:"
      srcmsg_len  equ  $-srcmsg

      dstmsg      db     10,"Destination Block:"
      dstmsg_len   equ  $-dstmsg
   
      space     db   "  "
      count     equ    5
      srcblk    db    11h,22h,33h,44h,55h
      dstblk      times 3 db 0
   
section .bss
           char_ans resb 4

%macro  print   2
  mov  rax,1
  mov  rdi,1
  mov  rsi,%1
  mov  rdx,%2
  syscall
%endmacro



%macro    read  2
      mov rax,0
      mov rdi,0
      mov rsi,%1
      mov rdx,%2
      syscall
%endmacro

%macro    exit   0
     mov rax, 60
     xor rdi,  rdi
     syscall
%endmacro


section .text
        global _start
_start:
      print msg,msg_len 
   
      print bfrmsg,bfrmsg_len
      print srcmsg,srcmsg_len
      mov   rsi,srcblk
      call display_block
      
      print dstmsg,dstmsg_len
      mov   rsi,dstblk-2
      call display_block
        
      call  BT_O
      print bfrmsg,bfrmsg_len
      print srcmsg,srcmsg_len
      mov  rsi,srcblk
      call  display_block
         
      print dstmsg,dstmsg_len
      mov   rsi,dstblk-2
      call display_block 
  
      print nline, nline_len
       exit

BT_O:
     mov    rsi,srcblk+4
     mov  rdi,dstblk+2
     mov  rcx,count
repeat:
      mov    al,[rsi]
      mov  [rdi],al
      dec   rsi
      dec   rdi
      loop  repeat
     
     ret
     
 display_block:
         mov     rbp,count
 back:
      mov   al,[rsi]
      push   rsi
      call display_8
      print space,1
      pop  rsi
      inc  rsi
      dec  rbp
      jnz  back
 
ret
 

display_8:
      mov   rsi,char_ans+1
      
      mov   rcx,2
      mov    rbx,16
      
  cnt:  mov   rdx,0
   
        div  rbx
        cmp  dl, 09h
        jbe  add30
        add  dl, 07h
  add30:
        add  dl, 30h
        mov  [rsi],dl
        dec   rsi
        dec  rcx
        jnz   cnt
          
        print     char_ans,2
        ret                 
  

def fact(a):
	i=1
	fact=1
	while i <= a:
		fact=fact*i
		print(f"{fact} = {i}")
		i=i+1
	return fact
ans=fact(4)
print(ans)	
		

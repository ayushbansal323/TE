class mycomplex:
	def __init__(self,a,b):
		self.r = a
		self.c = b
	def getitinstring(self): 
		return f"{a.r} + {a.c}i"
a=mycomplex(4,5)
print(a.getitinstring())

class myclass:
	r = 1
	c = 2	
a=myclass()
print(f"{a.r} + {a.c}i")

import random

def makesudoku():
	a=[[0,0,0],[0,0,0],[0,0,0]]
	iCount=int(random.uniform(1,9))
	jCount=int(random.uniform(1,4))
	i=0;
	do=1
	while(do == 1):
		a=[[0,0,0],[0,0,0],[0,0,0]]
		i=0;
		while i<iCount:
			j=int(random.uniform(1,4))-1
			no=int(random.uniform(1,4))
			if no not in a[j]:
				if no != a[0][i%3] and no != a[1][i%3] and no != a[2][i%3]: 
					a[j][i%3]=no
					i=1+i
		do=0
		for i1 in range(3):
			for j1 in range(3):
				ans=0
				if(j1 != 0):
					ans=ans+a[i1][0]
				if(j1 != 1):
					ans=ans+a[i1][1]
				if(j1 != 2):
					ans=ans+a[i1][2]


				if(i1 == 0):
					if (6-ans) == a[1][j1] or (6-ans) == a[2][j1]:
						do=1
				if(i1 == 1):
					if (6-ans) == a[0][j1] or (6-ans) == a[2][j1]:
						do=1
				if(i1 == 2):
					if (6-ans) == a[0][j1] or (6-ans) == a[1][j1] :
						do=1

				ans=0
				if(i1 != 0):
					ans=ans+a[0][j1]
				if(i1 != 1):
					ans=ans+a[1][j1]
				if(i1 != 2):
					ans=ans+a[2][j1]
				


				if(j1 == 0):
					if (6-ans) == a[i1][1] or (6-ans) == a[i1][2]:
						do=1
				if(j1 == 1):
					if (6-ans) == a[i1][0] or (6-ans) == a[i1][2]:
						do=1
				if(j1 == 2):
					if (6-ans) == a[i1][0] or (6-ans) == a[i1][1]:
						do=1
	return a



def main():
	print("sudoku");
	a=makesudoku()
	for i in range(3):
		print(a[i])

if __name__== "__main__":
  main()

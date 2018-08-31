my_list = [1,5,4,6,8,11,3,12]
my_lambda=lambda x:(x%2==0)
new_list = list(filter(my_lambda,my_list))
print(new_list)
new_list = list(map(my_lambda,my_list))
print(new_list)

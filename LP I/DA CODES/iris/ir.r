library(datasets)
#library(help="datasets")
data("iris")
names(iris)

dim(iris)

View(iris)
#-----------dataset details--------
str(iris)
#min
min(iris$Sepal.Length)
min(iris$Sepal.Width)
#max
max(iris$Sepal.Length)
max(iris$Sepal.Width)
#mean
mean(iris$Sepal.Length)
mean(iris$Sepal.Width)
#range
range(iris$Sepal.Length)

# Standard Deviation
sd(iris$Sepal.Length)
sd(iris$Sepal.Width)
#veriance
var(iris$Sepal.Length)

#percentaile
quantile(iris$Sepal.Length)
#specific use
quantile(iris$Sepal.Length,c(0.3,0.6,0.7))

#using hist function :simple call
h<-hist(iris$Sepal.Length, main="sepal length freuency-histogram",xlab="sepal length",xlim=c(3.5,8.5),col="blue")

# to display the details of histogram

#  using breaks and las
h<-hist(iris$Sepal.Length, main="sepal length freuency-histogram",xlab="sepal length",xlim=c(3.5,8.5),col="blue",labels = TRUE,breaks = 3,border ="green",las=2)

#write the brakes in following way as sometimes for fine details r does not shows by simply 
#writing breaks=12,you need to specify the vector
h<-hist(iris$Sepal.Length,breaks=c(4.3,4.6,4.9,5.2,5.5,5.8,6.1,6.4,6.7,7.0,7.3,7.6,7.9))


#box plots
#using boxpolt() function

boxplot(iris$Sepal.Length)

#this will display the summary -
summary(iris$Sepal.Length)



#combined boxplot for all 4 features

myboxplot<-boxplot(iris[,-5])


#to display outliers
myboxplot$out

summary(iris)
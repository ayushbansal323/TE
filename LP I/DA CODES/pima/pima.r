#Split the data into training data and test data
#split function is available in caTools
library(caTools)
library(e1071)
#install tool ->install package
mydata<-read.csv(file = "PimaIndiansDiabetes.csv",header = TRUE,sep = ",")
#display data

View(mydata)
#using sample.split from mydata with ratio of 70/30
temp_field <- sample.split(mydata,SplitRatio = 0.7)
#70% will be in training 
train <- subset(mydata,temp_field==TRUE)

#30% will be in testing
test <- subset(mydata,temp_field==FALSE)
#display 
head(train)
head(test)


#install e1071 pkg to use naive bayse
#ml pkg that is used for naive Bayes but can be used for SVM fuzzey clustering and so on

#building navie bayes
#naive bayes(formula data laplace suset na.action)

my_model<- naiveBayes(as.factor(train$Class)~.,train)

#make note the class cannot numric it needs to be categorical for naive bayes as specified 
#in the function hence as.factor internally maps 1 and 0 to categorical value
#this will generate a model for navie bayes whre the output class to be bredicated is outcome and the data is my data
#no other parameter as of now
#s3 method of formula

#to see summary of the probabilities calculated one may use
my_model

#predicting try putting  type-"Class" or type="raw" after the test data
pred1<-predict(my_model,test[,-9])
pred1


#generate confusion matrix
table(pred1,test$Class,dnn=c("predicted","Actual data"))


# to save the prediction
output<-cbind(test,pred1)
View(output)

library(e1071)
library(rpart)
library(caTools)
mydata<-read.csv("201805-capitalbikeshare-tripdata.csv",header=TRUE,sep=",")
subset_mydata<-mydata[,c(1,4,6,9)]

#View(mydata)
#%split
temp_field<-sample.split(subset_mydata,SplitRatio = 0.9)

train <- subset(subset_mydata,temp_field==TRUE)
test <- subset(subset_mydata,temp_field==FALSE)

summary(train)
summary(test)

head(train)
head(test)


fit <- rpart(train$Member.type~.,data=train,method = "class")

#to display

plot(fit)
text(fit)

#test data excluding the last cloumn\
pred<-predict(fit,newdata=test[,-4],type=("class"))

#mean of true predi

mean(pred==test$Member.type)


#display the output data

output<-cbind(test,pred)
View(output)


#fit<-rpart(train$Member.type~.,data = train,method = "class",control = rpart.control(cp=0,maxdepth = 8,minsplit = 100))
printcp(fit)


opt<-which.min(fit$cptable[,"xerror"])
cp<-fit$cptable[opt,"CP"]


#pruned tree
pruned_model <- prune(fit,cp)
#plot tree 
plot(fit)
text(fit)



#find proportion of correct predictions using test set

rpart_pruned_predict<-predict(pruned_model,test[,-4],type="class")

#higher the value of mean improvement

mean(rpart_pruned_predict==test$Member.type)

output1<-cbind(test,pred)

View(output1)






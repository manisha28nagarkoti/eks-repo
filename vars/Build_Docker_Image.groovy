def call(String image_name){
 sh'''
 sudo docker build -t $image_name nginx
 
 '''
}

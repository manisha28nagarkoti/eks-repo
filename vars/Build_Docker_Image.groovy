def call(String image_name){
 sh'''
 docker build -t $image_name .
 
 '''
}

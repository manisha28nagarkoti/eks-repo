def call(){
 sh'''
 docker build -t nginx home/ubuntu/Dockerfile
 
 '''
}
